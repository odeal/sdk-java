package com.odeal.sdk;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.List;

/**
 * Odeal Webhook İmza Doğrulama Sınıfı.
 *
 * <pre>{@code
 * // Spring Boot Controller
 * @PostMapping("/webhook")
 * public ResponseEntity<String> handleWebhook(
 *         @RequestBody String payload,
 *         @RequestHeader("X-Odeal-Signature") String signature) {
 *     if (!OdealWebhookVerifier.verifySignature(payload, signature, "your-secret")) {
 *         return ResponseEntity.status(401).body("Invalid signature");
 *     }
 *     // Webhook'u işle...
 *     return ResponseEntity.ok("OK");
 * }
 * }</pre>
 */
public class OdealWebhookVerifier {
    public static final String SIGNATURE_HEADER = "X-Odeal-Signature";
    public static final String TIMESTAMP_HEADER = "X-Odeal-Timestamp";
    public static final long DEFAULT_TOLERANCE_SECONDS = 300; // 5 dakika

    /**
     * Webhook imzasını doğrular.
     */
    public static boolean verifySignature(String payload, String signature, String webhookSecret) {
        if (payload == null || signature == null || webhookSecret == null) return false;
        if (payload.isEmpty() || signature.isEmpty() || webhookSecret.isEmpty()) return false;

        String expected = computeSignature(payload, webhookSecret);
        return MessageDigest.isEqual(
            expected.getBytes(StandardCharsets.UTF_8),
            signature.getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Birden fazla secret ile doğrulama (secret rotation desteği).
     * Herhangi bir secret ile imza eşleşirse true döner.
     */
    public static boolean verifySignature(String payload, String signature, List<String> webhookSecrets) {
        if (payload == null || signature == null || webhookSecrets == null) return false;
        for (String secret : webhookSecrets) {
            if (secret != null && !secret.isEmpty() && verifySignature(payload, signature, secret))
                return true;
        }
        return false;
    }

    /**
     * Timestamp kontrolü ile doğrulama (replay attack koruması).
     */
    public static boolean verifySignatureWithTimestamp(
            String payload, String signature, String timestamp,
            String webhookSecret, long toleranceSeconds) {
        long epochSeconds;
        try {
            epochSeconds = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            return false;
        }

        long diff = Math.abs(Instant.now().getEpochSecond() - epochSeconds);
        if (diff > toleranceSeconds) return false;

        String signedPayload = timestamp + "." + payload;
        return verifySignature(signedPayload, signature, webhookSecret);
    }

    /** Varsayılan tolerans ile timestamp doğrulama. */
    public static boolean verifySignatureWithTimestamp(
            String payload, String signature, String timestamp, String webhookSecret) {
        return verifySignatureWithTimestamp(payload, signature, timestamp, webhookSecret, DEFAULT_TOLERANCE_SECONDS);
    }

    /** Birden fazla secret + timestamp doğrulama (rotation + replay). */
    public static boolean verifySignatureWithTimestamp(
            String payload, String signature, String timestamp,
            List<String> webhookSecrets, long toleranceSeconds) {
        long epochSeconds;
        try {
            epochSeconds = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            return false;
        }
        long diff = Math.abs(Instant.now().getEpochSecond() - epochSeconds);
        if (diff > toleranceSeconds) return false;
        String signedPayload = timestamp + "." + payload;
        return verifySignature(signedPayload, signature, webhookSecrets);
    }

    /** Birden fazla secret + varsayılan tolerans. */
    public static boolean verifySignatureWithTimestamp(
            String payload, String signature, String timestamp, List<String> webhookSecrets) {
        return verifySignatureWithTimestamp(payload, signature, timestamp, webhookSecrets, DEFAULT_TOLERANCE_SECONDS);
    }

    /**
     * HMAC-SHA256 imza hesaplar.
     */
    public static String computeSignature(String payload, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("HMAC-SHA256 computation failed", e);
        }
    }
}
