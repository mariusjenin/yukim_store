package com.yukimstore.activity.unused;

import android.os.Bundle;

import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.middleware.MerchantMiddleware;
import com.yukimstore.middleware.StoreMiddleWare;

public abstract class ConnectedMerchantActivity extends ConnectedActivity {
    protected StoreMiddleWare store_middleWare;

    public ConnectedMerchantActivity(){
        super();
        type_user_middleware = new MerchantMiddleware();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ConnectionManager cm = ConnectionManager.getInstance();
//        cm.removeTokenUserFromPrefs(this);
        if(!connection_middleware.verify_and_redirect(this)){
            if(!type_user_middleware.verify_and_redirect(this)){
                store_middleWare.verify_and_redirect(this);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!connection_middleware.verify_and_redirect(this)){
            if(!type_user_middleware.verify_and_redirect(this)){
                store_middleWare.verify_and_redirect(this);
            }
        }
    }
}
