package com.odeal.sdk.resources;

import com.odeal.sdk.SdkResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SepetListelemeResource extends SdkResource {
    /**
     * Sepet Listele
     */
    default BasketListResponse listBaskets(
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
    default BasketListResponse listBaskets(
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
}