package com.yukimstore.activity.abstract_activity;

import com.yukimstore.activity.abstract_activity.MiddlewareActivity;
import com.yukimstore.middleware.NotConnectedMiddleware;

public abstract class NotConnectedActivity extends MiddlewareActivity {
    public NotConnectedActivity(){
        super();
        connection_middleware = new NotConnectedMiddleware();
    }
}