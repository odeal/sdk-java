package com.odeal.sdk;

import java.time.Instant;

/**
 * Circuit Breaker — art arda hata alındığında istekleri otomatik durdurur.
 * Thread-safe implementasyon (synchronized; durum geçişi + probe izni atomik yapılır).
 */
public class OdealCircuitBreaker {
    public enum State { CLOSED, OPEN, HALF_OPEN }

    private final int failureThreshold;
    private final long resetTimeoutMs;
    private final Object lock = new Object();

    private State state = State.CLOSED;
    private int failureCount = 0;
    private Instant lastFailureTime = Instant.MIN;
    private boolean halfOpenProbeInFlight = false;

    public OdealCircuitBreaker(int failureThreshold, long resetTimeoutMs) {
        this.failureThreshold = failureThreshold;
        this.resetTimeoutMs = resetTimeoutMs;
    }

    private boolean resetElapsed() {
        return Instant.now().toEpochMilli() - lastFailureTime.toEpochMilli() >= resetTimeoutMs;
    }

    /** Mevcut durumu döner (yan etkisiz okuma). */
    public State getCurrentState() {
        synchronized (lock) {
            if (state == State.OPEN && resetElapsed()) {
                return State.HALF_OPEN;
            }
            return state;
        }
    }

    /**
     * İsteğin geçip geçemeyeceğini kontrol eder. HALF_OPEN durumunda yalnızca TEK bir
     * probe (test) isteğine izin verilir; probe sonuçlanana kadar diğerleri reddedilir.
     */
    public boolean allowRequest() {
        synchronized (lock) {
            // OPEN -> HALF_OPEN: reset süresi dolduysa tek bir probe denemesine kapı aç.
            if (state == State.OPEN && resetElapsed()) {
                state = State.HALF_OPEN;
                halfOpenProbeInFlight = false;
            }

            switch (state) {
                case CLOSED:
                    return true;
                case HALF_OPEN:
                    if (halfOpenProbeInFlight) return false;
                    halfOpenProbeInFlight = true; // probe izni bu isteğe verildi
                    return true;
                default:
                    return false; // OPEN
            }
        }
    }

    public void recordSuccess() {
        synchronized (lock) {
            failureCount = 0;
            state = State.CLOSED;
            halfOpenProbeInFlight = false;
        }
    }

    public void recordFailure() {
        synchronized (lock) {
            lastFailureTime = Instant.now();

            if (state == State.HALF_OPEN) {
                // Probe başarısız -> doğrudan tekrar OPEN.
                state = State.OPEN;
                halfOpenProbeInFlight = false;
                return;
            }

            failureCount++;
            if (failureCount >= failureThreshold) {
                state = State.OPEN;
            }
        }
    }

    public void reset() {
        synchronized (lock) {
            failureCount = 0;
            state = State.CLOSED;
            halfOpenProbeInFlight = false;
        }
    }
}
