# Odeal SDK for Java

Odeal Entegrasyon SDK (Otomatik Üretildi)

## Gereksinimler

- Java 17 veya üzeri
- Gradle veya Maven

## Kurulum (Maven)

`pom.xml` dosyanıza ekleyin:

```xml
<dependency>
    <groupId>com.odeal</groupId>
    <artifactId>odeal-sdk</artifactId>
    <version>2.2.3</version>
</dependency>
```

## Hızlı Başlangıç

```java
import com.odeal.sdk.OdealClient;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.BasketRequest;
import com.odeal.sdk.models.BasketResponse;

public class Main {
    public static void main(String[] args) {
        // Yapılandırma
        OdealConfig config = new OdealConfig();
        config.setBaseUrl("https://api.odeal.com/v1");
        config.setSecretKey("gizli-anahtar");
        config.setMerchantKey("is-yeri-anahtari");
        config.setDebugMode(true);

        // İstemci oluşturma
        OdealClient client = new OdealClient(config);

        // Sepet oluşturma örneği
        try {
            BasketRequest request = new BasketRequest();
            // ... request alanlarını doldurun ...

            BasketResponse response = client.sepetSimple().createSimpleBasket(request, null, null, null, null, null);
            System.out.println("Sepet ID: " + response.getResult().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Özellikler

- ✅ Java 17 uyumlu
- ✅ `java.net.http.HttpClient` tabanlı (Dış bağımlılık minimum)
- ✅ Jackson ile JSON işleme
- ✅ Otomatik yapılandırma yönetimi
- ✅ Güçlü hata yönetimi (`OdealApiException`)

## Lisans

MIT
