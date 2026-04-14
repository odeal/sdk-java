package com.odeal.sdk;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Circuit Breaker — art arda hata alındığında istekleri otomatik durdurur.
 * Thread-safe implementasyon (AtomicReference + AtomicInteger).
 */
public class OdealCircuitBreaker {
    public enum State { CLOSED, OPEN, HALF_OPEN }

    private final int failureThreshold;
    private final long resetTimeoutMs;

    private final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private volatile Instant lastFailureTime = Instant.MIN;

    public OdealCircuitBreaker(int failureThreshold, long resetTimeoutMs) {
        this.failureThreshold = failureThreshold;
        this.resetTimeoutMs = resetTimeoutMs;
    }

    public State getCurrentState() {
        if (state.get() == State.OPEN) {
            long elapsed = Instant.now().toEpochMilli() - lastFailureTime.toEpochMilli();
            if (elapsed >= resetTimeoutMs) {
                state.compareAndSet(State.OPEN, State.HALF_OPEN);
            }
        }
        return state.get();
    }

    public boolean allowRequest() {
        State current = getCurrentState();
        return current == State.CLOSED || current == State.HALF_OPEN;
    }

    public void recordSuccess() {
        failureCount.set(0);
        state.set(State.CLOSED);
    }

    public void recordFailure() {
        int count = failureCount.incrementAndGet();
        lastFailureTime = Instant.now();
        if (count >= failureThreshold) {
            state.set(State.OPEN);
        }
    }

    public void reset() {
        failureCount.set(0);
        state.set(State.CLOSED);
    }
}
