package com.odeal.sdk.resources;

import com.odeal.sdk.SdkResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SepetAvansResource extends SdkResource {
    /**
     * Avans tahsilatı. Müşteri Bireysel veya Kurumsal olabilir. 'items' gönderilmez. `basketType` ADVANCE olmalıdır.
     */
    default BasketResponse createAdvanceBasket(
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
    default BasketResponse createAdvanceBasket(
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
}