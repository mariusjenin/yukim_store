package com.yukimstore.activity.unused;

import com.yukimstore.middleware.ConnectedMiddleware;
import com.yukimstore.middleware.ConnectionMiddleware;
import com.yukimstore.middleware.TypeUserMiddleware;

public abstract class ConnectedActivity extends MiddlewareActivity {
    protected ConnectionMiddleware connection_middleware;
    protected TypeUserMiddleware type_user_middleware;

    public ConnectedActivity(){
        super();
        connection_middleware = new ConnectedMiddleware();
    }
}
