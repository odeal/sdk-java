package com.odeal.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.odeal.sdk.exceptions.OdealApiException;
import com.odeal.sdk.exceptions.OdealAuthenticationException;
import com.odeal.sdk.exceptions.OdealForbiddenException;
import com.odeal.sdk.exceptions.OdealNotFoundException;
import com.odeal.sdk.exceptions.OdealRateLimitException;

import com.odeal.sdk.exceptions.OdealValidationException;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.reflect.Method;

public abstract class BaseResource {
    protected final HttpClient httpClient;
    protected final OdealConfig config;
    protected final ObjectMapper objectMapper;
    private static final String AGENT = "OdealSdkJavaClient/0.1.4";

    /**
     * Dedicated thread pool for async operations.
     * Uses daemon threads to avoid blocking JVM shutdown.
     * CachedThreadPool grows as needed and reclaims idle threads after 60s.
     */
    private static final ExecutorService ASYNC_EXECUTOR = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r, "odeal-sdk-async");
        t.setDaemon(true);
        return t;
    });
    
        private final OdealCircuitBreaker circuitBreaker;
        

    public BaseResource(OdealConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(config.getTimeoutMs()))
                .build();
        
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        
                if (config.isCircuitBreakerEnabled()) {
                    this.circuitBreaker = new OdealCircuitBreaker(config.getCircuitBreakerThreshold(), config.getCircuitBreakerResetMs());
                } else {
                    this.circuitBreaker = null;
                }
                
    }

    public OdealConfig getConfig() {
        return config;
    }

    // ==================== Public Send API ====================

    @SuppressWarnings("unchecked")
    public <T> T send(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> responseType) {
        return (T) sendInternal(method, path, body, queryParams, headerParams, responseType, false);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> sendList(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> itemType) {
        return (List<T>) sendInternal(method, path, body, queryParams, headerParams, itemType, true);
    }

    public void send(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams) {
        sendInternal(method, path, body, queryParams, headerParams, Void.class, false);
    }

    // ==================== Async API ====================

    @SuppressWarnings("unchecked")
    public <T> CompletableFuture<T> sendAsync(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> send(method, path, body, queryParams, headerParams, responseType), ASYNC_EXECUTOR);
    }

    @SuppressWarnings("unchecked")
    public <T> CompletableFuture<List<T>> sendListAsync(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> itemType) {
        return CompletableFuture.supplyAsync(() -> sendList(method, path, body, queryParams, headerParams, itemType), ASYNC_EXECUTOR);
    }

    public CompletableFuture<Void> sendVoidAsync(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams) {
        return CompletableFuture.runAsync(() -> send(method, path, body, queryParams, headerParams), ASYNC_EXECUTOR);
    }

    // ==================== Request Pipeline ====================

    private Object sendInternal(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<?> responseType, boolean isList) {
        
                // Circuit Breaker Guard
                if (circuitBreaker != null && !circuitBreaker.allowRequest()) {
                    throw new com.odeal.sdk.exceptions.OdealCircuitOpenException();
                }
                

        String fullUrl = buildUrl(config.getBaseUrl(), path, queryParams);
        String jsonBody = prepareBody(body, method);
        HttpRequest request = buildHttpRequest(method, fullUrl, jsonBody, body, headerParams);

        if (config.isDebugMode()) {
            logCurl(method, fullUrl, headerParams, jsonBody);
        }

        HttpResponse<String> response = executeWithRetry(request, method, fullUrl, headerParams, jsonBody);
        return processResponse(response, responseType, isList);
    }

    // ==================== URL Building ====================

    private static String buildUrl(String baseUrl, String path, Map<String, Object> queryParams) {
        String url = baseUrl.replaceAll("/$", "") + (path.startsWith("/") ? path : "/" + path);

        if (queryParams != null && !queryParams.isEmpty()) {
            StringBuilder queryString = new StringBuilder();
            queryParams.forEach((key, value) -> {
                if (value != null) {
                    if (queryString.length() > 0) queryString.append("&");
                    queryString.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
                    queryString.append("=");
                    queryString.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
                }
            });
            if (queryString.length() > 0) {
                url += "?" + queryString;
            }
        }

        return url;
    }

    // ==================== Body Preparation ====================

    private String prepareBody(Object body, String method) {
        // multipart gövdesi JSON'a serialize edilmez; buildHttpRequest ham bayt üretir.
        if (body == null || body instanceof MultipartBody) return null;

        try {
            fillConfigDefaults(body);
            
                        if (!config.isSkipClientValidation()) {
                            validateModel(body);
                        }
                        
            return objectMapper.writeValueAsString(body);
        } catch (com.odeal.sdk.exceptions.OdealValidationException ve) {
            throw ve;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization error", e);
        }
    }

    // ==================== HTTP Request Building ====================

    private HttpRequest buildHttpRequest(String method, String url, String jsonBody, Object body, Map<String, String> headerParams) {
        // multipart/form-data: ham bayt + boundary'li Content-Type üret.
        byte[] multipartBytes = null;
        String contentType = "application/json";
        if (body instanceof MultipartBody) {
            MultipartBody mp = (MultipartBody) body;
            contentType = mp.getContentType();
            multipartBytes = mp.toBytes();
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                // İstek (yanıt) zaman aşımı — connectTimeout yalnız bağlantı kurmayı kapsar,
                // yavaş yanıtları değil. Yapılandırılan timeout tüm istek/yanıta uygulanır.
                .timeout(Duration.ofMillis(config.getTimeoutMs()))
                .header("Content-Type", contentType)
                .header("Accept", "application/json")
                .header("X-ODEAL-AGENT", AGENT);

        // OAuth2 / Bearer: accessToken ayarlıysa Authorization header ekle (boşsa eklenmez)
        if (config.getAccessToken() != null && !config.getAccessToken().isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + config.getAccessToken());
        }

        if (headerParams != null) {
            headerParams.forEach((k, v) -> {
                if (v != null) requestBuilder.header(k, v);
            });
        }

        
                // Idempotency Key: POST/PUT/PATCH isteklerinde çift işlem koruması
                if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
                    requestBuilder.header("X-Odeal-Idempotency-Key", java.util.UUID.randomUUID().toString().replace("-", ""));
                }
                

        if (multipartBytes != null) {
            requestBuilder.method(method, HttpRequest.BodyPublishers.ofByteArray(multipartBytes));
        } else if (jsonBody != null) {
            requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(jsonBody));
        } else {
            requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
        }

        return requestBuilder.build();
    }

    // ==================== Retry Execution ====================

    private HttpResponse<String> executeWithRetry(HttpRequest request, String method, String fullUrl, Map<String, String> headerParams, String jsonBody) {
        int currentTry = 0;
        int maxRetries = config.getMaxRetryCount();

        while (true) {
            try {
                
                                // Interceptor: onBeforeRequest
                                invokeBeforeInterceptors(method, fullUrl, headerParams, jsonBody);
                                

                long startTime = System.currentTimeMillis();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                long durationMs = System.currentTimeMillis() - startTime;

                
                                // Interceptor: onAfterResponse
                                invokeAfterInterceptors(method, fullUrl, headerParams, jsonBody, response, durationMs);
                                

                boolean isRetryableFailure = isRetryable(response.statusCode());

                
                                // Devre-kıran: 5xx/429 = başarısızlık; diğer (2xx/4xx) = sunucu yanıt verdi (başarı sayılır).
                                // (Eskiden exhausted 5xx'te recordSuccess çağrılıp sayaç sıfırlanıyordu → devre açılmıyordu.)
                                if (circuitBreaker != null) {
                                    if (isRetryableFailure) circuitBreaker.recordFailure();
                                    else circuitBreaker.recordSuccess();
                                }
                                

                if (isRetryableFailure && currentTry < maxRetries) {
                    currentTry++;
                    long delayMs = calculateRetryDelay(currentTry, response);
                    debugLog("Request failed with " + response.statusCode() + ". Retrying in " + delayMs + "ms. Attempt " + currentTry + " of " + maxRetries, "warn");
                    Thread.sleep(delayMs);
                    continue;
                }

                return response;

            } catch (IOException e) {
                
                                if (circuitBreaker != null) circuitBreaker.recordFailure();
                                
                if (currentTry < maxRetries) {
                    currentTry++;
                    long delayMs = (long) (Math.pow(2, currentTry) * 500);
                    debugLog("Network error: " + e.getMessage() + ". Retrying in " + delayMs + "ms. Attempt " + currentTry + " of " + maxRetries, "warn");
                    try { Thread.sleep(delayMs); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); throw new RuntimeException(ie); }
                    continue;
                }
                // Ağ hatası / zaman aşımı → Odeal exception hiyerarşisi (diğer dillerle tutarlı).
                throw new OdealApiException("Network/timeout error during API call", 0, e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Request interrupted", e);
            }
        }
    }

    // Zengin hata hiyerarşisi: HTTP status → spesifik exception tipi.
    private static OdealApiException mapApiException(int statusCode, String body) {
        switch (statusCode) {
            case 401: return new OdealAuthenticationException("Authentication failed.", body);
            case 403: return new OdealForbiddenException("Access denied.", body);
            case 404: return new OdealNotFoundException("Resource not found.", body);
            case 429: return new OdealRateLimitException("Rate limit exceeded.", body, null);
            default:  return new OdealApiException("API Error: " + statusCode, statusCode, body);
        }
    }

    private static boolean isRetryable(int statusCode) {
        return statusCode >= 500 || statusCode == 429;
    }

    private static long calculateRetryDelay(int attempt, HttpResponse<String> response) {
        long delayMs = (long) (Math.pow(2, attempt) * 500);
        
                String retryAfter = response.headers().firstValue("Retry-After").orElse(null);
                if (retryAfter != null) {
                    try { delayMs = Long.parseLong(retryAfter) * 1000; } catch (NumberFormatException ignored) {}
                }
                
        return delayMs;
    }

    
        // ==================== Interceptors ====================
    
        private void invokeBeforeInterceptors(String method, String url, Map<String, String> headers, String body) {
            for (OdealInterceptor interceptor : config.getInterceptors()) {
                
                        String safeBody = config.isMaskSensitiveData() ? OdealSanitizer.sanitizeJson(body) : body;
                        Map<String, String> safeHeaders = config.isMaskSensitiveData() ? OdealSanitizer.sanitizeHeaders(headers) : headers;
                        
                OdealInterceptor.RequestContext reqCtx = new OdealInterceptor.RequestContext(method, url, safeHeaders, safeBody);
                interceptor.onBeforeRequest(reqCtx);
            }
        }
    
        private void invokeAfterInterceptors(String method, String url, Map<String, String> headers, String body, HttpResponse<String> response, long durationMs) {
            for (OdealInterceptor interceptor : config.getInterceptors()) {
                
                        String safeBody = config.isMaskSensitiveData() ? OdealSanitizer.sanitizeJson(body) : body;
                        String safeRespBody = config.isMaskSensitiveData() ? OdealSanitizer.sanitizeJson(response.body()) : response.body();
                        Map<String, String> safeHeaders = config.isMaskSensitiveData() ? OdealSanitizer.sanitizeHeaders(headers) : headers;
                        
                OdealInterceptor.ResponseContext respCtx = new OdealInterceptor.ResponseContext();
                respCtx.setStatusCode(response.statusCode());
                respCtx.setBody(safeRespBody);
                respCtx.setDurationMs(durationMs);
                respCtx.setRequest(new OdealInterceptor.RequestContext(method, url, safeHeaders, safeBody));
                interceptor.onAfterResponse(respCtx);
            }
        }
    
        // ==================== Validation ====================
    
        private void validateModel(Object body) {
            if (body == null) return;
            List<String> errors = new java.util.ArrayList<>();
            for (java.lang.reflect.Field field : body.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(body);
                    // Zorunlu alan kontrolü: null veya boş string ise hata
                    for (java.lang.annotation.Annotation ann : field.getAnnotations()) {
                        if (ann.annotationType().getSimpleName().equals("Required") || 
                            ann.annotationType().getSimpleName().equals("NotNull") ||
                            ann.annotationType().getSimpleName().equals("NotEmpty")) {
                            if (value == null || (value instanceof String && ((String)value).isEmpty())) {
                                errors.add(field.getName() + " alanı zorunludur.");
                            }
                            break;
                        }
                    }
                } catch (IllegalAccessException ignored) {}
            }
            if (!errors.isEmpty()) {
                debugLog("Validation Failed: " + String.join(", ", errors), "warn");
                throw new com.odeal.sdk.exceptions.OdealValidationException("Validation hatası: " + String.join("; ", errors), errors);
            }
        }
        

    // ==================== Response Processing ====================

    private Object processResponse(HttpResponse<String> response, Class<?> responseType, boolean isList) {
        if (response.statusCode() >= 400) {
            throw mapApiException(response.statusCode(), response.body());
        }

        if (response.body() == null || response.body().isEmpty()) {
            return null;
        }

        try {
            return deserializeResponse(response.body(), responseType, isList);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization error", e);
        }
    }

    private Object deserializeResponse(String content, Class<?> responseType, boolean isList) throws IOException {
        JsonNode rootNode = objectMapper.readTree(content);

        boolean shouldUnwrap = false;
        if (rootNode.isObject() && rootNode.has("result")) {
            if (isList) {
                shouldUnwrap = true;
            } else {
                shouldUnwrap = !hasResultField(responseType);
            }
        }

        if (shouldUnwrap) {
            rootNode = rootNode.get("result");
        }

        if (isList) {
            return objectMapper.readerForListOf(responseType).readValue(rootNode);
        } else {
            if (responseType == Void.class) return null;
            return objectMapper.treeToValue(rootNode, responseType);
        }
    }

    private boolean hasResultField(Class<?> type) {
        if (type == null) return false;
        try {
            type.getDeclaredField("result");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    // ==================== Client Defaults ====================

    private void fillConfigDefaults(Object body) {
        if (body == null) return;
        try {
            Class<?> clazz = body.getClass();
            setConfigValue(body, clazz, "getMerchantKey", "setMerchantKey", config.getMerchantKey());
            setConfigValue(body, clazz, "getExternalDeviceKey", "setExternalDeviceKey", config.getExternalDeviceKey());
        } catch (Exception e) {
            // Ignore reflection errors
        }
    }

    private void setConfigValue(Object body, Class<?> clazz, String getterName, String setterName, String value) {
        if (value == null) return;
        try {
            Method getter = clazz.getMethod(getterName);
            Object currentVal = getter.invoke(body);
            if (currentVal == null) {
                Method setter = clazz.getMethod(setterName, String.class);
                setter.invoke(body, value);
            }
        } catch (Exception ignored) {}
    }

    // ==================== Debug Logging ====================

    protected void debugLog(String message, String level) {
        
                org.slf4j.Logger logger = config.getLogger();
                if (!config.isDebugMode() && !"error".equals(level) && !"warn".equals(level)) {
                    return;
                }
                switch (level) {
                    case "error" -> logger.error(message);
                    case "warn" -> logger.warn(message);
                    case "info" -> logger.info(message);
                    default -> logger.debug(message);
                }
                
    }

    private void logCurl(String method, String url, Map<String, String> headers, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append("curl -X ").append(method).append(" '").append(url).append("'");
        sb.append(" -H 'Content-Type: application/json'");
        sb.append(" -H 'Accept: application/json'");
        sb.append(" -H 'X-ODEAL-AGENT: ").append(AGENT).append("'");
        
        if (headers != null) {
            headers.forEach((k, v) -> {
                
                                String safeVal = v;
                                if (config.isMaskSensitiveData() && (k.toLowerCase().contains("secret") || k.toLowerCase().contains("key") || k.toLowerCase().contains("authorization"))) {
                                    safeVal = "***";
                                }
                                sb.append(" -H '").append(k).append(": ").append(safeVal).append("'");
                                
            });
        }
        if (body != null) {
            
                        String safeBody = body;
                        if (config.isMaskSensitiveData()) {
                            safeBody = OdealSanitizer.sanitizeJson(safeBody);
                        }
                        sb.append(" -d '").append(safeBody.replace("'", "'\\''")).append("'");
                        
        }
        debugLog(sb.toString(), "info");
    }
}
