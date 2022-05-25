package com.yukimstore.activity;

import com.yukimstore.activity.unused.ConnectedMerchantActivity;
import com.yukimstore.middleware.MerchantWithoutStoreMiddleware;

public class ConnectedMerchantWithoutStoreActivity extends ConnectedMerchantActivity {
    public ConnectedMerchantWithoutStoreActivity(){
        super();
        store_middleWare = new MerchantWithoutStoreMiddleware();
    }
}
