package com.yukimstore.activity;

import android.os.Bundle;

import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.ConnectionMiddleware;
import com.yukimstore.middleware.NotConnectedMiddleware;
import com.yukimstore.middleware.TypeUserMiddleware;

public abstract class ConnectedActivity extends MiddlewareActivity {
    protected ConnectionMiddleware connection_middleware;
    protected TypeUserMiddleware type_user_middleware;

    public ConnectedActivity(){
        super();
        connection_middleware = new ConnectedMiddleware();
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
