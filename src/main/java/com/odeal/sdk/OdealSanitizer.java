package com.odeal.sdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Hassas verileri maskeleyen yardımcı sınıf.
 * Interceptor body verileri ve debug loglarında hassas verilerin sızmasını engeller.
 */
public final class OdealSanitizer {

    private static final String[] SENSITIVE_FIELDS = {
        "password", "cvv", "cvc", "cardNumber", "card_number", "pan",
        "expiryDate", "expiry_date", "securityCode", "security_code",
        "secretKey", "secret_key", "token", "accessToken", "access_token",
        "refreshToken", "refresh_token", "authorization",
        "tckn", "tcKimlikNo", "tcKimlik", "identityNumber", "nationalId",
        "iban", "phone", "phoneNumber", "telephone", "gsm",
        "email", "eMail", "mail", "address", "adres"
    };

    private static final String[] SENSITIVE_HEADER_KEYWORDS = {
        "secret", "key", "authorization", "token", "cookie", "session"
    };

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Set<String> SENSITIVE_FIELD_SET =
        Arrays.stream(SENSITIVE_FIELDS).map(String::toLowerCase).collect(Collectors.toCollection(HashSet::new));

    private OdealSanitizer() {}

    /**
     * JSON string içindeki hassas alanları maskeler.
     *
     * <p>JSON ağacı parse edilerek iç içe nesneler, diziler ve string olmayan değerler
     * (sayı, boolean) dahil tüm hassas alanlar maskelenir. Geçerli JSON değilse regex
     * tabanlı güvenli yedeğe düşülür.</p>
     */
    public static String sanitizeJson(String json) {
        if (json == null || json.isEmpty()) return json;
        try {
            JsonNode root = MAPPER.readTree(json);
            maskNode(root);
            return MAPPER.writeValueAsString(root);
        } catch (Exception e) {
            return sanitizeJsonRegex(json);
        }
    }

    /**
     * JSON ağacında hassas alanları recursive olarak maskeler.
     */
    private static void maskNode(JsonNode node) {
        if (node instanceof ObjectNode obj) {
            List<String> fields = new ArrayList<>();
            obj.fieldNames().forEachRemaining(fields::add);
            for (String name : fields) {
                if (SENSITIVE_FIELD_SET.contains(name.toLowerCase())) {
                    obj.put(name, "***");
                } else {
                    maskNode(obj.get(name));
                }
            }
        } else if (node instanceof ArrayNode arr) {
            for (JsonNode item : arr) {
                maskNode(item);
            }
        }
    }

    /**
     * Parse edilemeyen içerik için regex tabanlı yedek maskeleme.
     */
    private static String sanitizeJsonRegex(String json) {
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
