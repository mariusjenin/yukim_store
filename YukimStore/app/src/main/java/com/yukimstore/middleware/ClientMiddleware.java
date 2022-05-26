package com.yukimstore.middleware;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.yukimstore.activity.unused.MiddlewareActivity;
import com.yukimstore.activity.used.client.MenuActivityC;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur l'ecran d'identification
 * si l'utilisateur n'est pas un client
 */
public class ClientMiddleware extends TypeUserMiddleware {
    public boolean verify_and_redirect(MiddlewareActivity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        if(cm.getUtilisateur().is_merchant){
            redirect(activity);
            return true;
        }
        return false;
    }
    public void redirect(MiddlewareActivity activity){
        activity.finish();
        Intent intent = new Intent(activity, MenuActivityC.class);
        activity.setIsRedirected(true);
        activity.startActivity(intent);
    }
}
