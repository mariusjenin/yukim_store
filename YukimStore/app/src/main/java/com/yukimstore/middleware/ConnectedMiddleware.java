package com.yukimstore.middleware;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.yukimstore.activity.used.IdentificationActivity;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur l'ecran d'identification
 * si l'utilisateur n'est pas connecté
 */
public class ConnectedMiddleware extends ConnectionMiddleware {
    public boolean verify_and_redirect(Activity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        boolean isConnected = cm.isConnected();
        if(!isConnected){
            redirect(activity);
            return true;
        }
        return false;
    }
    public void redirect(Activity activity){
        activity.finish();
        Intent intent = new Intent(activity, IdentificationActivity.class);
        activity.startActivity(intent);
    }
}
