package com.yukimstore.activity.abstract_activity;

import android.os.Bundle;

import com.yukimstore.middleware.MerchantMiddleware;
import com.yukimstore.middleware.StoreMiddleWare;

public abstract class ConnectedMerchantActivity extends ConnectedActivity {
    protected StoreMiddleWare store_middleWare;

    public ConnectedMerchantActivity(){
        super();
        type_user_middleware = new MerchantMiddleware();
    }

    public boolean verify_and_redirect() {
        boolean redirected = super.verify_and_redirect();
        if(redirected) return true;
        return store_middleWare.verify_and_redirect(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ConnectionManager cm = ConnectionManager.getInstance();
//        cm.removeTokenUserFromPrefs(this);
        verify_and_redirect();
    }


    @Override
    protected void onResume() {
        super.onResume();
        verify_and_redirect();
    }
}
