package com.odeal.sdk;

/**
 * Odeal API ortam tanımları.
 *
 * <p>SDK'nın hangi ortama bağlanacağını belirler.
 * Varsayılan olarak {@link #STAGING} kullanılır.</p>
 *
 * <h3>Kullanım:</h3>
 * <pre>{@code
 * OdealConfig config = OdealConfig.builder()
 *     .secretKey("sk_xxx")
 *     .environment(OdealEnvironment.PRODUCTION)
 *     .build();
 * }</pre>
 */
public enum OdealEnvironment {
    /** Test/geliştirme ortamı */
    STAGING("https://stage.odealapp.com/api/v1"),
    /** Canlı (production) ortamı */
    PRODUCTION("https://api.odeal.com/v1");

    private final String baseUrl;

    OdealEnvironment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Ortam için API base URL'ini döner.
     * @return Base URL string
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * String'den ortam çözümler. "production"/"prod" → PRODUCTION, diğer → STAGING.
     */
    public static OdealEnvironment fromString(String value) {
        if (value == null) return STAGING;
        switch (value.toLowerCase()) {
            case "production":
            case "prod":
                return PRODUCTION;
            default:
                return STAGING;
        }
    }
}
