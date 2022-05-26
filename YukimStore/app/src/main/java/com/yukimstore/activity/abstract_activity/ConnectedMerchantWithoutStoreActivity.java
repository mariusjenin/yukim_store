package com.yukimstore.activity.abstract_activity;

import com.yukimstore.activity.abstract_activity.ConnectedMerchantActivity;
import com.yukimstore.middleware.MerchantWithoutStoreMiddleware;

public abstract class ConnectedMerchantWithoutStoreActivity extends ConnectedMerchantActivity {
    public ConnectedMerchantWithoutStoreActivity(){
        super();
        store_middleWare = new MerchantWithoutStoreMiddleware();
    }
}
