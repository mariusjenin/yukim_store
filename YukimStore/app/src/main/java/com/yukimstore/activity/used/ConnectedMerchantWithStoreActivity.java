package com.yukimstore.activity.used;

import com.yukimstore.activity.unused.ConnectedMerchantActivity;
import com.yukimstore.middleware.MerchantWithStoreMiddleWare;

public class ConnectedMerchantWithStoreActivity extends ConnectedMerchantActivity {
    public ConnectedMerchantWithStoreActivity(){
        super();
        store_middleWare = new MerchantWithStoreMiddleWare();
    }
}