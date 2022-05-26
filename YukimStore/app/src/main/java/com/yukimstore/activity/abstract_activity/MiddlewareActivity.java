package com.yukimstore.activity.abstract_activity;

import android.os.Bundle;

import com.yukimstore.middleware.ConnectionMiddleware;

public abstract class MiddlewareActivity extends YukimActivity{
    protected ConnectionMiddleware connection_middleware;
    protected boolean is_redirected;

    public boolean verify_and_redirect() {
        return connection_middleware.verify_and_redirect(this);
    }

    public void setIsRedirected(boolean is_redirected){
        this.is_redirected = is_redirected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ConnectionManager cm = ConnectionManager.getInstance();
//        cm.removeTokenUserFromPrefs(this);
        is_redirected = false;
        verify_and_redirect();
    }


    @Override
    protected void onResume() {
        super.onResume();
        is_redirected = false;
        verify_and_redirect();
    }
}
