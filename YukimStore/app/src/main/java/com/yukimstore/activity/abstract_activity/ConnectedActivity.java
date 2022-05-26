package com.yukimstore.activity.abstract_activity;

import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.TypeUserMiddleware;

public abstract class ConnectedActivity extends MiddlewareActivity {
    protected TypeUserMiddleware type_user_middleware;

    public ConnectedActivity(){
        super();
        connection_middleware = new ConnectedMiddleware();
    }

    @Override
    public boolean verify_and_redirect() {
        boolean redirected = super.verify_and_redirect();
        if(redirected) return true;
        return type_user_middleware.verify_and_redirect(this);
    }
}
