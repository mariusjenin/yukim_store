package com.yukimstore.activity;

import com.yukimstore.middleware.ClientMiddleware;
import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.MerchantMiddleware;

public class ConnectedMerchantActivity extends ConnectedActivity {
    public ConnectedMerchantActivity(){
        super();
        type_user_middleware = new MerchantMiddleware();
    }
}
