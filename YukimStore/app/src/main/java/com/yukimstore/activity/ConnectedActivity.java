package com.yukimstore.activity;

import com.yukimstore.middleware.ConnectedMiddleware;

public abstract class ConnectedActivity extends MiddlewareActivity {
    public ConnectedActivity(){
        super();
        middleware = new ConnectedMiddleware();
    }
}
