package com.odeal.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Hassas verileri maskeleyen yardımcı sınıf.
 * Interceptor body verileri ve debug loglarında hassas verilerin sızmasını engeller.
 */
public final class OdealSanitizer {

    private static final String[] SENSITIVE_FIELDS = {
        "password", "cvv", "cvc", "cardNumber", "card_number", "pan",
        "expiryDate", "expiry_date", "securityCode", "security_code",
        "secretKey", "secret_key", "token", "accessToken", "access_token",
        "refreshToken", "refresh_token", "authorization"
    };

    private static final String[] SENSITIVE_HEADER_KEYWORDS = {
        "secret", "key", "authorization", "token"
    };

    private OdealSanitizer() {}

    /**
     * JSON string içindeki hassas alanları maskeler.
     */
    public static String sanitizeJson(String json) {
        if (json == null || json.isEmpty()) return json;
        String result = json;
        for (String field : SENSITIVE_FIELDS) {
            result = Pattern.compile(
                "(\"" + Pattern.quote(field) + "\"\\s*:\\s*\")[^\"]*\"",
                Pattern.CASE_INSENSITIVE
            ).matcher(result).replaceAll("$1***\"");
        }
        return result;
    }

    /**
     * Header map'indeki hassas değerleri maskeler.
     */
    public static Map<String, String> sanitizeHeaders(Map<String, String> headers) {
        if (headers == null) return new HashMap<>();
        Map<String, String> sanitized = new HashMap<>(headers.size());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String lower = entry.getKey().toLowerCase();
            boolean isSensitive = false;
            for (String keyword : SENSITIVE_HEADER_KEYWORDS) {
                if (lower.contains(keyword)) { isSensitive = true; break; }
            }
            sanitized.put(entry.getKey(), isSensitive ? "***" : entry.getValue());
        }
        return sanitized;
    }
}
