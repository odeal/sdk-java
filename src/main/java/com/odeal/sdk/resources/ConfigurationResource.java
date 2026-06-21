package com.odeal.sdk.resources;

import com.odeal.sdk.BaseResource;
import com.odeal.sdk.OdealConfig;
import com.odeal.sdk.models.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Configuration API islemleri.
 */
public class ConfigurationResource extends BaseResource {

    public ConfigurationResource(OdealConfig config) {
        super(config);
    }
    /**
     * Konfigürasyon Kaydet
     */
    public void saveConfiguration(
        ConfigurationRequest request
    ) {
        saveConfiguration(
            request,
            null,
            null,
            null
        );
    }

    /**
     * Konfigürasyon Kaydet
     */
    public void saveConfiguration(
        ConfigurationRequest request,
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/configuration";

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

        send(
            "POST",
            path,
            request,
            queryParams,
            headerParams
        );
    }
    /**
     * Konfigürasyon Getir
     */
    public ConfigurationResponse getConfiguration(
    ) {
        return getConfiguration(
            
            null,
            null,
            null
        );
    }

    /**
     * Konfigürasyon Getir
     */
    public ConfigurationResponse getConfiguration(
        String secretKey,
        String merchantKey,
        String baseUrl
    ) {
        String path = "/configuration";

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
            "GET",
            path,
            null,
            queryParams,
            headerParams,
            ConfigurationResponse.class
        );
    }
}