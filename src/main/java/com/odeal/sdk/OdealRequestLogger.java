package com.odeal.sdk;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Hazır Odeal Request Logger middleware'i.
 * <p>
 * Interceptor pipeline üzerine inşa edilmiştir. Tüm HTTP isteklerini ve
 * yanıtlarını yapılandırılabilir şekilde loglar.
 * </p>
 * <p>Örnek kullanım:</p>
 * <pre>{@code
 * OdealRequestLogger logger = new OdealRequestLogger.Builder()
 *     .level("info")
 *     .maskFields(Arrays.asList("cvv", "password"))
 *     .build();
 *
 * OdealConfig config = new OdealConfig.Builder()
 *     .secretKey("sk_xxx")
 *     .addInterceptor(logger)
 *     .build();
 * }</pre>
 */
public class OdealRequestLogger implements OdealInterceptor {

    private static final List<String> DEFAULT_MASK_FIELDS = List.of(
        "password", "cvv", "cvc", "cardNumber", "card_number", "pan",
        "expiryDate", "expiry_date", "securityCode", "security_code",
        "secretKey", "secret_key", "token", "accessToken", "access_token",
        "refreshToken", "refresh_token", "authorization",
        "tckn", "tcKimlikNo", "tcKimlik", "identityNumber", "nationalId",
        "iban", "phone", "phoneNumber", "telephone", "gsm",
        "email", "eMail", "mail", "address", "adres"
    );

    private final String level;
    private final long minDurationMs;
    private final List<String> maskFields;
    private final boolean logBody;
    private final boolean logResponseBody;
    private final org.slf4j.Logger logger;

    private OdealRequestLogger(Builder builder) {
        this.level = builder.level;
        this.minDurationMs = builder.minDurationMs;
        this.maskFields = List.copyOf(builder.maskFields);
        this.logBody = builder.logBody;
        this.logResponseBody = builder.logResponseBody;
        this.logger = org.slf4j.LoggerFactory.getLogger(OdealRequestLogger.class);
    }

    @Override
    public void onBeforeRequest(OdealInterceptor.RequestContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("→ ").append(context.getMethod()).append(" ").append(context.getUrl());
        if (logBody && context.getBody() != null) {
            sb.append("\n  Body: ").append(maskSensitive(context.getBody()));
        }
        log(sb.toString(), "info");
    }

    @Override
    public void onAfterResponse(OdealInterceptor.ResponseContext context) {
        if (context.getDurationMs() < minDurationMs) return;

        int status = context.getStatusCode();
        String lvl = status >= 500 ? "error" : status >= 400 ? "warn" : "info";

        StringBuilder sb = new StringBuilder();
        sb.append("← ").append(status).append(" ");
        if (context.getRequest() != null) {
            sb.append(context.getRequest().getMethod()).append(" ").append(context.getRequest().getUrl());
        }
        sb.append(" (").append(context.getDurationMs()).append("ms)");

        if (logResponseBody && context.getBody() != null) {
            String truncated = context.getBody().length() > 500 ? context.getBody().substring(0, 500) : context.getBody();
            sb.append("\n  Body: ").append(maskSensitive(truncated));
        }
        log(sb.toString(), lvl);
    }

    private void log(String message, String lvl) {
        String formatted = "[ODEAL " + lvl.toUpperCase() + "] " + message;
        switch (lvl) {
            case "error" -> logger.error(formatted);
            case "warn" -> logger.warn(formatted);
            default -> logger.info(formatted);
        }
    }

    private String maskSensitive(String text) {
        String result = text;
        for (String field : maskFields) {
            result = Pattern.compile("(\"" + Pattern.quote(field) + "\"\\s*:\\s*\")[^\"]+(\")", Pattern.CASE_INSENSITIVE)
                    .matcher(result)
                    .replaceAll("$1***$2");
        }
        return result;
    }

    // ==================== Builder ====================

    public static class Builder {
        private String level = "info";
        private long minDurationMs = 0;
        private List<String> maskFields = DEFAULT_MASK_FIELDS;
        private boolean logBody = true;
        private boolean logResponseBody = false;

        public Builder level(String level) { this.level = level; return this; }
        public Builder minDurationMs(long ms) { this.minDurationMs = ms; return this; }
        public Builder maskFields(List<String> fields) { this.maskFields = fields == null ? DEFAULT_MASK_FIELDS : List.copyOf(fields); return this; }
        public Builder logBody(boolean log) { this.logBody = log; return this; }
        public Builder logResponseBody(boolean log) { this.logResponseBody = log; return this; }

        public OdealRequestLogger build() { return new OdealRequestLogger(this); }
    }
}
