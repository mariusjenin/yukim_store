package com.yukimstore.activity;

import android.os.Bundle;

import com.yukimstore.middleware.NotConnectedMiddleware;

public abstract class NotConnectedActivity extends MiddlewareActivity{
    public NotConnectedActivity(){
        super();
        connection_middleware = new NotConnectedMiddleware();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connection_middleware.verify_and_redirect(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        connection_middleware.verify_and_redirect(this);

    }
}