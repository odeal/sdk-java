package com.odeal.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP istek/yanıt pipeline'ına araya girmek için kullanılan interceptor interface'i.
 * <p>
 * Örnek kullanım:
 * <pre>{@code
 * OdealInterceptor loggingInterceptor = new OdealInterceptor() {
 *     @Override
 *     public void onBeforeRequest(RequestContext context) {
 *         System.out.println("[REQUEST] " + context.getMethod() + " " + context.getUrl());
 *     }
 *
 *     @Override
 *     public void onAfterResponse(ResponseContext context) {
 *         System.out.println("[RESPONSE] " + context.getStatusCode() + " (" + context.getDurationMs() + "ms)");
 *     }
 * };
 * }</pre>
 */
public interface OdealInterceptor {

    /** HTTP isteği gönderilmeden önce çağrılır. */
    default void onBeforeRequest(RequestContext context) {}

    /** HTTP yanıtı alındıktan sonra çağrılır. */
    default void onAfterResponse(ResponseContext context) {}

    /**
     * İstek bağlamı — interceptor'a gönderilen istek bilgileri.
     */
    class RequestContext {
        private String method = "";
        private String url = "";
        private Map<String, String> headers = new HashMap<>();
        private String body;
        private final Map<String, Object> metadata = new HashMap<>();

        public RequestContext() {}

        public RequestContext(String method, String url, Map<String, String> headers, String body) {
            this.method = method;
            this.url = url;
            this.headers = headers != null ? headers : new HashMap<>();
            this.body = body;
        }

        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public Map<String, String> getHeaders() { return headers; }
        public void setHeaders(Map<String, String> headers) { this.headers = headers; }
        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
        public Map<String, Object> getMetadata() { return metadata; }
    }

    /**
     * Yanıt bağlamı — interceptor'a gönderilen yanıt bilgileri.
     */
    class ResponseContext {
        private int statusCode;
        private Map<String, String> headers = new HashMap<>();
        private String body;
        private long durationMs;
        private RequestContext request;
        private final Map<String, Object> metadata = new HashMap<>();

        public ResponseContext() {}

        public int getStatusCode() { return statusCode; }
        public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
        public Map<String, String> getHeaders() { return headers; }
        public void setHeaders(Map<String, String> headers) { this.headers = headers; }
        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
        public long getDurationMs() { return durationMs; }
        public void setDurationMs(long durationMs) { this.durationMs = durationMs; }
        public RequestContext getRequest() { return request; }
        public void setRequest(RequestContext request) { this.request = request; }
        public Map<String, Object> getMetadata() { return metadata; }
    }
}
