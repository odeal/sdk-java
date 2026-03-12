package com.odeal.sdk;

import com.odeal.sdk.models.*;
import com.odeal.sdk.resources.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OdealClient extends BaseResource implements SdkResource, SepetSimpleResource, SepetAvansResource, SepetCariResource, SepetFoodCardResource, SepetListelemeResource, SepetSilmeResource, OdemeResource, KonfigurasyonResource, BirimlerResource, RaporlamaResource {

    public OdealClient(OdealConfig config) {
        super(config);
    }

    @Override
    public OdealConfig getConfig() {
        return config;
    }
}
