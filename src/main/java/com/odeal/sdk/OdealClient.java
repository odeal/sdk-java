package com.odeal.sdk;

import com.odeal.sdk.models.*;
import com.odeal.sdk.resources.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Odeal API ana istemci sinifi.
 * Tum API islemlerine dogrudan erisim saglar.
 */
public class OdealClient {

    private final OdealConfig config;
    private final BasketResource _basket;
    private final PaymentResource _payment;
    private final ConfigurationResource _configuration;
    private final UnitResource _unit;
    private final ReportResource _report;

    public OdealClient(OdealConfig config) {
        this.config = config;
        this._basket = new BasketResource(config);
        this._payment = new PaymentResource(config);
        this._configuration = new ConfigurationResource(config);
        this._unit = new UnitResource(config);
        this._report = new ReportResource(config);
    }

    /**
     * Yapilandirma nesnesini doner.
     */
    public OdealConfig getConfig() {
        return config;
    }

    // ══════════════════════════════════════════════════════════════════════════
    // RESOURCE ACCESSORS
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Basket API islemlerine dogrudan erisim.
     */
    public BasketResource basket() {
        return _basket;
    }

    /**
     * Payment API islemlerine dogrudan erisim.
     */
    public PaymentResource payment() {
        return _payment;
    }

    /**
     * Configuration API islemlerine dogrudan erisim.
     */
    public ConfigurationResource configuration() {
        return _configuration;
    }

    /**
     * Unit API islemlerine dogrudan erisim.
     */
    public UnitResource unit() {
        return _unit;
    }

    /**
     * Report API islemlerine dogrudan erisim.
     */
    public ReportResource report() {
        return _report;
    }

    // ══════════════════════════════════════════════════════════════════════════
    // FACADE METHODS
    // ══════════════════════════════════════════════════════════════════════════
    /**
     * Standart ürün satışı. Müşteri Bireysel veya Kurumsal olabilir. 'items' alanı zorunludur.
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public BasketResponse createSimpleBasket(
        BasketRequest request
    ) {
        return _basket.createSimpleBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Standart ürün satışı. Müşteri Bireysel veya Kurumsal olabilir. 'items' alanı zorunludur.
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public BasketResponse createSimpleBasket(
        BasketRequest request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _basket.createSimpleBasket(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır.
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public BasketResponse createAdvanceBasket(
        BasketRequestAdvance request
    ) {
        return _basket.createAdvanceBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır.
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public BasketResponse createAdvanceBasket(
        BasketRequestAdvance request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _basket.createAdvanceBasket(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Cari hesap tahsilatı. Müşteri Kurumsal olmalıdır. `basketType` CURRENT_ACCOUNT olmalıdır.
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public BasketResponse createCurrentAccountBasket(
        BasketRequestCurrentAccount request
    ) {
        return _basket.createCurrentAccountBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Cari hesap tahsilatı. Müşteri Kurumsal olmalıdır. `basketType` CURRENT_ACCOUNT olmalıdır.
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public BasketResponse createCurrentAccountBasket(
        BasketRequestCurrentAccount request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _basket.createCurrentAccountBasket(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Yemek kartı işlemleri. `receiptInfo` ve içindeki `foodCardBrandId` zorunludur.
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public BasketResponse createFoodCardBasket(
        BasketRequestFoodCard request
    ) {
        return _basket.createFoodCardBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Yemek kartı işlemleri. `receiptInfo` ve içindeki `foodCardBrandId` zorunludur.
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public BasketResponse createFoodCardBasket(
        BasketRequestFoodCard request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _basket.createFoodCardBasket(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Sepet Listele
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public BasketListResponse listBaskets(
    ) {
        return _basket.listBaskets(
            
            null,
            null,
            null,
            null
        );
    }

    /**
     * Sepet Listele
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public BasketListResponse listBaskets(
        String secretKey,
        String merchantKey,
        String externalDeviceKey,
        String baseUrl
    ) {
        return _basket.listBaskets(
            
            secretKey,
            merchantKey,
            externalDeviceKey,
            baseUrl
        );
    }
    /**
     * Sepet Sil
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public void deleteBasket(
        String referenceCode
    ) {
        _basket.deleteBasket(
            
            referenceCode,
            null,
            null,
            null
        );
    }

    /**
     * Sepet Sil
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public void deleteBasket(
        String referenceCode,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        _basket.deleteBasket(
            
            referenceCode,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Ödeme İptali
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public Object cancelPayment(
        Object request
    ) {
        return _payment.cancelPayment(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Ödeme İptali
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public Object cancelPayment(
        Object request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _payment.cancelPayment(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Konfigürasyon Kaydet
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public void saveConfiguration(
        ConfigurationRequest request
    ) {
        _configuration.saveConfiguration(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Konfigürasyon Kaydet
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public void saveConfiguration(
        ConfigurationRequest request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        _configuration.saveConfiguration(
            request,
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * Birimleri Listele
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public List<Unit> listUnits(
    ) {
        return _unit.listUnits(
            
            null,
            null,
            null
        );
    }

    /**
     * Birimleri Listele
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public List<Unit> listUnits(
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        return _unit.listUnits(
            
            secretKey,
            merchantKey,
            baseUrl
        );
    }
    /**
     * İşlem Raporu
     * <p>Auth parametreleri config'den otomatik doldurulur.</p>
     */
    public List<TransactionReport> getTransactionReport(
        String beginDate,
        String endDate
    ) {
        return _report.getTransactionReport(
            
            beginDate,
            endDate,
            null,
            null,
            null,
            null,
            null
        );
    }

    /**
     * İşlem Raporu
     * <p>Tum parametreleri acikca belirtmek icin kullanin.</p>
     */
    public List<TransactionReport> getTransactionReport(
        String beginDate,
        String endDate,
        String secretKey,
        String merchantKey,
        String externalDeviceKey,
        String basketReferenceCode,
        String baseUrl
    ) {
        return _report.getTransactionReport(
            
            beginDate,
            endDate,
            secretKey,
            merchantKey,
            externalDeviceKey,
            basketReferenceCode,
            baseUrl
        );
    }
}
