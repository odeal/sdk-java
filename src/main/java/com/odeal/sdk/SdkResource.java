package com.odeal.sdk;

import java.util.List;
import java.util.Map;

public interface SdkResource {
    <T> T send(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> responseType);
    
    <T> List<T> sendList(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams, Class<T> itemType);
    
    void send(String method, String path, Object body, Map<String, Object> queryParams, Map<String, String> headerParams);
    
    OdealConfig getConfig();
}
