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
import java.lang.reflect.Method;

public abstract class BaseResource {
    protected final HttpClient httpClient;
    protected final OdealConfig config;
    protected final ObjectMapper objectMapper;
    private static final String AGENT = "OdealSdkJavaClient/2.2.8";

    public BaseResource(OdealConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

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

    private Object sendInternal(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<?> responseType, boolean isList) {
        try {
            return sendAsyncInternal(method, path, body, queryParams, headerParams, responseType, isList).get();
        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof OdealApiException) {
                throw (OdealApiException) e.getCause();
            }
            throw new RuntimeException("Unexpected error during API call", e);
        }
    }

    private CompletableFuture<Object> sendAsyncInternal(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<?> responseType, boolean isList) {
        String baseUrl = config.getBaseUrl().replaceAll("/$", "");
        String fullUrl = baseUrl + (path.startsWith("/") ? path : "/" + path);

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
                fullUrl += "?" + queryString;
            }
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        // Custom headers (method level overrides)
        if (headerParams != null) {
            headerParams.forEach((k, v) -> {
                if (v != null) requestBuilder.header(k, v);
            });
        }

        // Agent header'ını her zaman ez (Kullanıcı değiştiremesin)
        requestBuilder.header("X-ODEAL-AGENT", AGENT);

        // Body
        String jsonBody = null;
        if (body != null) {
            try {
                fillConfigDefaults(body);
                jsonBody = objectMapper.writeValueAsString(body);
                requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(jsonBody));
            } catch (JsonProcessingException e) {
                CompletableFuture<Object> failed = new CompletableFuture<>();
                failed.completeExceptionally(new RuntimeException("Serialization error", e));
                return failed;
            }
        } else {
            requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
        }

        if (config.isDebugMode()) {
            logCurl(method, fullUrl, headerParams, jsonBody);
        }

        return httpClient.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() >= 400) {
                        throw new OdealApiException("API Error: " + response.statusCode(), response.statusCode(), response.body());
                    }

                    if (response.body() == null || response.body().isEmpty()) {
                        return null;
                    }

                    try {
                        JsonNode rootNode = objectMapper.readTree(response.body());
                        
                        // Wrapper unwrapping logic:
                        // 1. If it's a list, we MUST unwrap "result" if present (because List<T> matches the inner array, not {result: []}).
                        // 2. If it's a single object, we only unwrap "result" if the target model DOES NOT have a "result" field itself.
                        //    (e.g. BasketResponse has "result" field, so we keep the wrapper. But if we mapped directly to an internal model, we might need to unwrap).
                        
                        boolean shouldUnwrap = false;
                        if (rootNode.isObject() && rootNode.has("result")) {
                             if (isList) {
                                 shouldUnwrap = true;
                             } else {
                                 // Check if target type already expects "result"
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
                    } catch (IOException e) {
                        throw new RuntimeException("Deserialization error", e);
                    }
                });
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

    private void logCurl(String method, String url, Map<String, String> headers, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append("curl -X ").append(method).append(" '").append(url).append("'");
        // Headers (Default ones added manually for log accuracy)
        sb.append(" -H 'Content-Type: application/json'");
        sb.append(" -H 'Accept: application/json'");
        sb.append(" -H 'X-ODEAL-AGENT: ").append(AGENT).append("'");
        
        if (headers != null) {
            headers.forEach((k, v) -> sb.append(" -H '").append(k).append(": ").append(v).append("'"));
        }
        if (body != null) {
            sb.append(" -d '").append(body).append("'");
        }
        System.out.println("[ODEAL DEBUG] " + sb.toString());
    }
}