package com.yukimstore.activity.used;

import android.os.Bundle;

import com.yukimstore.activity.unused.MiddlewareActivity;
import com.yukimstore.middleware.NotConnectedMiddleware;

public class NotConnectedActivity extends MiddlewareActivity {
    public NotConnectedActivity(){
        super();
        connection_middleware = new NotConnectedMiddleware();
    }
}