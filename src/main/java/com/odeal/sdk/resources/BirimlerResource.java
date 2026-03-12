package com.odeal.sdk.resources;

import com.odeal.sdk.SdkResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BirimlerResource extends SdkResource {
    /**
     * Birimleri Listele
     */
    default List<Unit> listUnits(
    ) {
        return listUnits(
            
            null,
            null,
            null
        );
    }

    /**
     * Birimleri Listele
     */
    default List<Unit> listUnits(
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/unit";

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

        return sendList(
            "GET",
            path,
            null,
            queryParams,
            headerParams,
            Unit.class
        );
    }
}