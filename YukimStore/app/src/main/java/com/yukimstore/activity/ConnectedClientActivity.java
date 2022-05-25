package com.yukimstore.activity;

import android.os.Bundle;

import com.yukimstore.activity.unused.ConnectedActivity;
import com.yukimstore.middleware.ClientMiddleware;

public class ConnectedClientActivity extends ConnectedActivity {
    public ConnectedClientActivity(){
        super();
        type_user_middleware = new ClientMiddleware();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ConnectionManager cm = ConnectionManager.getInstance();
//        cm.removeTokenUserFromPrefs(this);
        if(!connection_middleware.verify_and_redirect(this)){
            type_user_middleware.verify_and_redirect(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!connection_middleware.verify_and_redirect(this)){
            type_user_middleware.verify_and_redirect(this);
        }
    }
}
