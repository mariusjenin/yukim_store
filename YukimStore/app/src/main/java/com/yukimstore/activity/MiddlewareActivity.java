package com.yukimstore.activity;

import android.os.Bundle;

import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.ConnectionMiddleware;
import com.yukimstore.middleware.TypeUserMiddleware;
import com.yukimstore.middleware.YukimMiddleware;

public abstract class MiddlewareActivity extends YukimActivity{
    protected ConnectionMiddleware connection_middleware;
}
