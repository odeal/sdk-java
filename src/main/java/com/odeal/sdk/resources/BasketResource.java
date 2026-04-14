package com.odeal.sdk.resources;

import com.odeal.sdk.BaseResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Basket API islemleri.
 */
public class BasketResource extends BaseResource {

    public BasketResource(OdealConfig config) {
        super(config);
    }
    /**
     * Standart ürün satışı. Müşteri Bireysel veya Kurumsal olabilir. 'items' alanı zorunludur.
     */
    public BasketResponse createSimpleBasket(
        BasketRequest request
    ) {
        return createSimpleBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Standart ürün satışı. Müşteri Bireysel veya Kurumsal olabilir. 'items' alanı zorunludur.
     */
    public BasketResponse createSimpleBasket(
        BasketRequest request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/basket";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }

        return send(
            "POST",
            path,
            request,
            queryParams,
            headerParams,
            BasketResponse.class
        );
    }
    /**
     * Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır.
     */
    public BasketResponse createAdvanceBasket(
        BasketRequestAdvance request
    ) {
        return createAdvanceBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır.
     */
    public BasketResponse createAdvanceBasket(
        BasketRequestAdvance request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/basket/advance";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }

        return send(
            "POST",
            path,
            request,
            queryParams,
            headerParams,
            BasketResponse.class
        );
    }
    /**
     * Cari hesap tahsilatı. Müşteri Kurumsal olmalıdır. `basketType` CURRENT_ACCOUNT olmalıdır.
     */
    public BasketResponse createCurrentAccountBasket(
        BasketRequestCurrentAccount request
    ) {
        return createCurrentAccountBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Cari hesap tahsilatı. Müşteri Kurumsal olmalıdır. `basketType` CURRENT_ACCOUNT olmalıdır.
     */
    public BasketResponse createCurrentAccountBasket(
        BasketRequestCurrentAccount request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/basket/current-account";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }

        return send(
            "POST",
            path,
            request,
            queryParams,
            headerParams,
            BasketResponse.class
        );
    }
    /**
     * Yemek kartı işlemleri. `receiptInfo` ve içindeki `foodCardBrandId` zorunludur.
     */
    public BasketResponse createFoodCardBasket(
        BasketRequestFoodCard request
    ) {
        return createFoodCardBasket(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Yemek kartı işlemleri. `receiptInfo` ve içindeki `foodCardBrandId` zorunludur.
     */
    public BasketResponse createFoodCardBasket(
        BasketRequestFoodCard request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/basket/foodCard";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }

        return send(
            "POST",
            path,
            request,
            queryParams,
            headerParams,
            BasketResponse.class
        );
    }
    /**
     * Sepet Listele
     */
    public BasketListResponse listBaskets(
    ) {
        return listBaskets(
            
            null,
            null,
            null,
            null
        );
    }

    /**
     * Sepet Listele
     */
    public BasketListResponse listBaskets(
        String secretKey,
        String merchantKey,
        String externalDeviceKey,
        String baseUrl
    ) {
        String path = "/basket/list";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }
        if (externalDeviceKey != null) {
            headerParams.put("externalDeviceKey", String.valueOf(externalDeviceKey));
        }
        else if (getConfig().getExternalDeviceKey() != null) {
             headerParams.put("externalDeviceKey", String.valueOf(getConfig().getExternalDeviceKey()));
        }

        return send(
            "GET",
            path,
            null,
            queryParams,
            headerParams,
            BasketListResponse.class
        );
    }
    /**
     * Sepet Sil
     */
    public void deleteBasket(
        String referenceCode
    ) {
        deleteBasket(
            
            referenceCode,
            null,
            null,
            null
        );
    }

    /**
     * Sepet Sil
     */
    public void deleteBasket(
        String referenceCode,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/basket/delete";

        Map<String, Object> queryParams = new HashMap<>();

        Map<String, String> headerParams = new HashMap<>();
        if (secretKey != null) {
            headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(secretKey));
        }
        else if (getConfig().getSecretKey() != null) {
             headerParams.put("X-ODEAL-SECRET-KEY", String.valueOf(getConfig().getSecretKey()));
        }
        if (merchantKey != null) {
            headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(merchantKey));
        }
        else if (getConfig().getMerchantKey() != null) {
             headerParams.put("X-ODEAL-MERCHANT-KEY", String.valueOf(getConfig().getMerchantKey()));
        }
        if (referenceCode != null) {
            headerParams.put("referenceCode", String.valueOf(referenceCode));
        }

        send(
            "DELETE",
            path,
            null,
            queryParams,
            headerParams
        );
    }
}