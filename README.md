# Odeal SDK for Java

> Odeal Entegrasyon SDK (Otomatik Üretildi)

> **Version:** 2.17.7 | **License:** MIT | **Auto-Generated** by Odeal SDK Generator


## Installation

### Maven

```xml
<dependency>
    <groupId>com.odeal</groupId>
    <artifactId>odeal-sdk</artifactId>
    <version>2.17.7</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.odeal:odeal-sdk:2.17.7'
```

## Requirements

- Java 17 or later
- No external HTTP dependencies (`java.net.http.HttpClient`)

## Quick Start

```java
import com.odeal.sdk.OdealClient;
import com.odeal.sdk.OdealConfig;

OdealConfig config = new OdealConfig();
config.setSecretKey("your-secret-key");
config.setMerchantKey("your-merchant-key");

OdealClient client = new OdealClient(config);

// SDK iki kullanım biçimini de destekler — ikisi de eşdeğerdir, aynı metoda gider:
BasketResponse response  = client.createSimpleBasket(request);          // 1) Flat (doğrudan)
BasketResponse response2 = client.basket().createSimpleBasket(request); // 2) Grouped (resource üzerinden)
```

## Configuration

### Builder Pattern

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

### Environment Variables

```java
// Reads: ODEAL_SECRET_KEY, ODEAL_MERCHANT_KEY
OdealClient client = OdealAutoConfiguration.createFromEnvironment();
```

### Spring Boot

```java
@Bean
public OdealClient odealClient() {
    return OdealAutoConfiguration.createFromEnvironment();
}
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

## Error Handling

```java
import com.odeal.sdk.exceptions.*;

try {
    client.basket().createSimpleBasket(request);
} catch (OdealRateLimitException e) {
    System.out.println("Rate limit! Retry: " + e.getRetryAfterSeconds() + "s");
} catch (OdealAuthenticationException e) {
    System.out.println("Invalid API keys!");
} catch (OdealNotFoundException e) {
    System.out.println("Resource not found");
} catch (OdealApiException e) {
    System.out.println("API Error: " + e.getStatusCode() + " - " + e.getMessage());
}
```

## Circuit Breaker

```java
OdealConfig config = OdealConfig.builder()
    .secretKey("sk_xxx")
    .merchantKey("mk_xxx")
    .circuitBreakerEnabled(true)
    .circuitBreakerThreshold(5)    // 5 consecutive failures → circuit opens
    .circuitBreakerResetMs(60000)  // 60s cooldown before half-open test
    .build();

try {
    client.basket().createSimpleBasket(request);
} catch (OdealCircuitOpenException e) {
    System.out.println("Circuit open — requests temporarily blocked.");
}
```

## Request Logger

Built-in `OdealRequestLogger` middleware for production HTTP traffic logging:

```java
OdealRequestLogger logger = new OdealRequestLogger.Builder()
    .level("info")
    .maskFields(Arrays.asList("password", "cvv", "cardNumber", "tckn", "iban", "email", "phone", "address"))
    .logBody(true)
    .logResponseBody(false)
    .minDurationMs(0)
    .build();

OdealConfig config = OdealConfig.builder()
    .secretKey("sk_xxx")
    .addInterceptor(logger)
    .build();

// Output (SLF4J):
// [ODEAL INFO] → POST https://api.odeal.com/basket/simple
//   Body: {"merchantKey":"mk_xxx","password":"***"}
// [ODEAL INFO] ← 200 POST https://api.odeal.com/basket/simple (142ms)
```

## Timeout & Retry

```java
OdealConfig config = OdealConfig.builder()
    .timeoutMs(60000)       // Default: 30000ms
    .maxRetryCount(5)       // Default: 3
    .build();

// Automatic retry on 5xx and 429 with exponential backoff
// Retry-After header is respected
```

## Async Usage

```java
import java.util.concurrent.CompletableFuture;

// Single async request
CompletableFuture<BasketResponse> future = client.basket()
    .createSimpleBasketAsync(request);
future.thenAccept(response -> System.out.println("Result: " + response));

// Parallel requests
CompletableFuture.allOf(future1, future2, future3).join();
```

## Webhook Verification

Gelen Odeal webhook'larının gerçekten Odeal'den geldiğini HMAC-SHA256 imzasıyla doğrulayın.
İmza `X-Odeal-Signature` header'ında, **ham gövde** üzerinden hesaplanarak gelir.

```java
import com.odeal.sdk.OdealWebhookVerifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    @PostMapping("/webhooks/odeal")
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("X-Odeal-Signature") String signature) {

        if (!OdealWebhookVerifier.verifySignature(payload, signature, "your-webhook-secret")) {
            return ResponseEntity.status(401).body("Invalid webhook signature");
        }

        // İmza geçerli — webhook olayını işle
        return ResponseEntity.ok().build();
    }
}
```

Replay koruması için timestamp doğrulamalı sürüm (önerilir):

```java
boolean valid = OdealWebhookVerifier.verifySignatureWithTimestamp(
    payload, signature, timestamp, "your-webhook-secret"); // varsayılan 5 dk tolerans
```

## Features

- ✅ Java 17+ compatible
- ✅ Builder pattern for type-safe configuration
- ✅ Environment variable support (Spring Boot compatible)
- ✅ Interceptor pipeline (request/response hooks)
- ✅ Rich error hierarchy (Auth, Forbidden, NotFound, RateLimit, Validation)
- ✅ Automatic retry with exponential backoff & Retry-After header
- ✅ Configurable timeout
- ✅ Idempotency key injection for POST/PUT/PATCH
- ✅ Client-side input validation
- ✅ Sensitive data masking in debug logs
- ✅ Response unwrapping (`{"result": [...]}` → auto-extract)
- ✅ Circuit breaker pattern
- ✅ Request Logger middleware
- ✅ Async support (CompletableFuture)
- ✅ SLF4J structured logging
- ✅ `java.net.http.HttpClient` based (minimal dependencies)
- ✅ Webhook signature verification
- ✅ Health check utility

## API Reference
### BasketResource

| Method | Description |
|:-------|:------------|
| `createSimpleBasket()` | Standart ürün satışı. Müşteri Bireysel veya Kurumsal olabilir. 'items' alanı zorunludur. |
| `createAdvanceBasket()` | Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır. |
| `createCurrentAccountBasket()` | Cari hesap tahsilatı. Müşteri Kurumsal olmalıdır. `basketType` CURRENT_ACCOUNT olmalıdır. |
| `createFoodCardBasket()` | Yemek kartı işlemleri. `receiptInfo` ve içindeki `foodCardBrandId` zorunludur. |
| `listBaskets()` | Sepet Listele |
| `deleteBasket()` | Sepet Sil |
| `deleteAllBaskets()` | Tüm Sepetleri Sil |
### PaymentResource

| Method | Description |
|:-------|:------------|
| `cancelPayment()` | Ödeme İptali |
### ConfigurationResource

| Method | Description |
|:-------|:------------|
| `saveConfiguration()` | Konfigürasyon Kaydet |
| `getConfiguration()` | Konfigürasyon Getir |
### UnitResource

| Method | Description |
|:-------|:------------|
| `listUnits()` | Birimleri Listele |
### ReportResource

| Method | Description |
|:-------|:------------|
| `getTransactionReport()` | İşlem Raporu |

## 🔐 Güvenlik & Doğrulama

Bu paket **Sigstore (cosign)** ile imzalanır ve doğrulanabilir kanıtlarla (attestation) yayınlanır:

- **İmza** — keyless cosign imzası; kimlik, yayınlayan CI workflow'una OIDC ile bağlanır
- **SBOM** — CycloneDX yazılım malzeme listesi (imzalı attestation)
- **Zafiyet taraması** — grype taraması (imzalı attestation)
- **Provenance** — SLSA build-provenance attestation'ı

### Doğrulama

```bash
# Paket imzası
cosign verify-blob \
  --bundle sign.bundle.json \
  --certificate-oidc-issuer https://token.actions.githubusercontent.com \
  --certificate-identity-regexp "<yayın-workflow-url>" \
  <paket-dosyası>

# SBOM attestation
cosign verify-blob-attestation \
  --bundle sbom.bundle.json --type cyclonedx \
  --certificate-oidc-issuer https://token.actions.githubusercontent.com \
  --certificate-identity-regexp "<yayın-workflow-url>" \
  <paket-dosyası>
```

Güvenlik açığı bildirimi ve tam talimatlar için: [`SECURITY.md`](./SECURITY.md)


## License

MIT
