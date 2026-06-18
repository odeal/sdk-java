package com.odeal.sdk.resources;

import com.odeal.sdk.BaseResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Payment API islemleri.
 */
public class PaymentResource extends BaseResource {

    public PaymentResource(OdealConfig config) {
        super(config);
    }
    /**
     * Ödeme İptali
     */
    public CancelPaymentResponse cancelPayment(
        CancelPaymentRequest request
    ) {
        return cancelPayment(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Ödeme İptali
     */
    public CancelPaymentResponse cancelPayment(
        CancelPaymentRequest request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/payment/cancel";

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
            "PUT",
            path,
            request,
            queryParams,
            headerParams,
            CancelPaymentResponse.class
        );
    }
}