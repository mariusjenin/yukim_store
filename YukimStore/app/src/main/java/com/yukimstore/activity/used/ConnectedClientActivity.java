package com.yukimstore.activity.used;

import android.os.Bundle;

import com.yukimstore.activity.unused.ConnectedActivity;
import com.yukimstore.middleware.ClientMiddleware;

public class ConnectedClientActivity extends ConnectedActivity {
    public ConnectedClientActivity(){
        super();
        type_user_middleware = new ClientMiddleware();
    }
}
