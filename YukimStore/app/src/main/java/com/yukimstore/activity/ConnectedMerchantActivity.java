package com.yukimstore.activity;

import com.yukimstore.middleware.ClientMiddleware;
import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.MerchantMiddleware;

public class ConnectedMerchantActivity extends ConnectedActivity {
    public ConnectedMerchantActivity(){
        super();
        connection_middleware = new ConnectedMiddleware();
        type_user_middleware = new MerchantMiddleware();
    }
}
