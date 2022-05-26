package com.yukimstore.activity.abstract_activity;

import com.yukimstore.activity.abstract_activity.ConnectedMerchantActivity;
import com.yukimstore.middleware.MerchantWithStoreMiddleWare;

public abstract class ConnectedMerchantWithStoreActivity extends ConnectedMerchantActivity {
    public ConnectedMerchantWithStoreActivity(){
        super();
        store_middleWare = new MerchantWithStoreMiddleWare();
    }
}