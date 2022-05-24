package com.yukimstore.activity;

import com.yukimstore.middleware.ClientMiddleware;
import com.yukimstore.middleware.ConnectedMiddleware;

public class ConnectedClientActivity extends ConnectedActivity{
    public ConnectedClientActivity(){
        super();
        connection_middleware = new ConnectedMiddleware();
        type_user_middleware = new ClientMiddleware();
    }
}
