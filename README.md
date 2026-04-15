# Odeal SDK for Java

Odeal Entegrasyon SDK (Otomatik Üretildi)

## Gereksinimler

- Java 17 veya üzeri
- Maven veya Gradle

## Kurulum (Maven)

```xml
<dependency>
    <groupId>com.odeal</groupId>
    <artifactId>odeal-sdk</artifactId>
    <version>2.2.14</version>
</dependency>
```

## Hızlı Başlangıç

```java
import com.odeal.sdk.OdealClient;
import com.odeal.sdk.OdealConfig;

OdealConfig config = new OdealConfig();
config.setSecretKey("gizli-anahtar");
config.setMerchantKey("is-yeri-anahtari");

OdealClient client = new OdealClient(config);
```

## Builder Pattern

```java
OdealConfig config = OdealConfig.builder()
    .secretKey("sk_xxx")
    .merchantKey("mk_xxx")
    .baseUrl("https://api.odeal.com/v1")
    .debugMode(true)
    .timeoutMs(60000)
    .maxRetryCount(5)
    .build();

OdealClient client = new OdealClient(config);
```

## Environment Variables

```java
// ODEAL_SECRET_KEY, ODEAL_MERCHANT_KEY env'den okur
OdealClient client = OdealAutoConfiguration.createFromEnvironment();
```

## Interceptors

```java
import com.odeal.sdk.OdealInterceptor;

OdealInterceptor loggingInterceptor = new OdealInterceptor() {
    @Override
    public void onBeforeRequest(RequestContext ctx) {
        System.out.println("→ " + ctx.getMethod() + " " + ctx.getUrl());
    }

    @Override
    public void onAfterResponse(ResponseContext ctx) {
        System.out.println("← " + ctx.getStatusCode() + " (" + ctx.getDurationMs() + "ms)");
    }
};

OdealConfig config = OdealConfig.builder()
    .secretKey("sk_xxx")
    .merchantKey("mk_xxx")
    .addInterceptor(loggingInterceptor)
    .build();
```

## Hata Yönetimi

```java
import com.odeal.sdk.exceptions.*;

try {
    client.sepetSimple().createSimpleBasket(request);
} catch (OdealRateLimitException e) {
    System.out.println("Rate limit! Retry: " + e.getRetryAfterSeconds() + "s");
} catch (OdealAuthenticationException e) {
    System.out.println("Geçersiz API anahtarı!");
} catch (OdealNotFoundException e) {
    System.out.println("Kaynak bulunamadı");
} catch (OdealApiException e) {
    System.out.println("API Hatası: " + e.getStatusCode() + " - " + e.getMessage());
}
```

## Özellikler

- ✅ Java 17 uyumlu
- ✅ Builder pattern ile tip-güvenli yapılandırma
- ✅ Ortam değişkeni desteği (Spring Boot uyumlu)
- ✅ Interceptor pipeline (request/response hooks)
- ✅ Zengin hata hiyerarşisi (Auth, Forbidden, NotFound, RateLimit, Validation)
- ✅ Otomatik retry & exponential backoff + Retry-After header
- ✅ Yapılandırılabilir timeout
- ✅ Idempotency key injection (POST/PUT/PATCH)
- ✅ Client-side validation
- ✅ Hassas veri maskeleme (debug loglar)
- ✅ Response unwrapping (`{"result": [...]}` → otomatik çözüm)
- ✅ Circuit breaker pattern
- ✅ Async desteği (CompletableFuture)
- ✅ SLF4J structured logging
- ✅ `java.net.http.HttpClient` tabanlı (minimum bağımlılık)

## Circuit Breaker

```java
OdealConfig config = new OdealConfig();
config.setCircuitBreakerEnabled(true);
config.setCircuitBreakerThreshold(5);    // 5 ardışık hata → devre açılır
config.setCircuitBreakerResetMs(60000);  // 60s sonra test isteği

try {
    client.basket().createSimpleBasket(request);
} catch (OdealException e) {
    if (e.getMessage().contains("Circuit breaker")) {
        System.out.println("Devre açık — istekler geçici olarak durduruldu.");
    }
}
```

## Async Kullanım

```java
import java.util.concurrent.CompletableFuture;

// Tek async istek
CompletableFuture<BasketResponse> future = client.basket().sendAsync("POST", "/basket", request, null, headers, BasketResponse.class);
future.thenAccept(response -> System.out.println("Sonuç: " + response));

// Paralel istekler
CompletableFuture.allOf(future1, future2, future3).join();
```

## Timeout & Retry

```java
OdealConfig config = new OdealConfig();
config.setTimeoutMs(60000);      // Varsayılan: 30000ms
config.setMaxRetryCount(5);       // Varsayılan: 3
```

## Lisans

MIT

> **Version:** 2.2.14 | **License:** MIT | **Auto-Generated** by Odeal SDK Generator

