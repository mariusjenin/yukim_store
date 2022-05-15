package com.yukimstore.activity;

import android.os.Bundle;

import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.middleware.YukimMiddleware;

public abstract class MiddlewareActivity extends YukimActivity{
    protected YukimMiddleware middleware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ADD these 2 lines to prevent from auto login
//        ConnectionManager cm =ConnectionManager.getInstance();
//        cm.removeTokenUserFromPrefs(this);
        middleware.verify_and_redirect(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        middleware.verify_and_redirect(this);
    }
}
