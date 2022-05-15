package com.yukimstore.activity;

import com.yukimstore.middleware.NotConnectedMiddleware;

public abstract class NotConnectedActivity extends MiddlewareActivity{
    public NotConnectedActivity(){
        super();
        middleware = new NotConnectedMiddleware();
    }
}