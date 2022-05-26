package com.yukimstore.activity.abstract_activity;

import com.yukimstore.activity.abstract_activity.ConnectedActivity;
import com.yukimstore.middleware.ClientMiddleware;

public abstract class ConnectedClientActivity extends ConnectedActivity {
    public ConnectedClientActivity(){
        super();
        type_user_middleware = new ClientMiddleware();
    }
}
