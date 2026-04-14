package com.odeal.sdk.resources;

import com.odeal.sdk.BaseResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Report API islemleri.
 */
public class ReportResource extends BaseResource {

    public ReportResource(OdealConfig config) {
        super(config);
    }
    /**
     * İşlem Raporu
     */
    public List<TransactionReport> getTransactionReport(
        String beginDate,
        String endDate
    ) {
        return getTransactionReport(
            
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
        String path = "/report/transactions";

        Map<String, Object> queryParams = new HashMap<>();
        if (beginDate != null) {
            queryParams.put("beginDate", beginDate);
        }
        if (endDate != null) {
            queryParams.put("endDate", endDate);
        }
        if (externalDeviceKey != null) {
            queryParams.put("externalDeviceKey", externalDeviceKey);
        }
        else if (getConfig().getExternalDeviceKey() != null) {
             queryParams.put("externalDeviceKey", getConfig().getExternalDeviceKey());
        }
        if (basketReferenceCode != null) {
            queryParams.put("basketReferenceCode", basketReferenceCode);
        }

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
            TransactionReport.class
        );
    }
}