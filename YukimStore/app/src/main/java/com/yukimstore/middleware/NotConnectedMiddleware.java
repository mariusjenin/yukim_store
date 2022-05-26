package com.yukimstore.middleware;

import android.content.Intent;

import com.yukimstore.activity.abstract_activity.MiddlewareActivity;
import com.yukimstore.activity.concrete_activity.client.MenuActivityC;
import com.yukimstore.activity.concrete_activity.merchant.MenuActivityM;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur l'ecran d'accueil
 * si l'utilisateur est connecté
 */
public class NotConnectedMiddleware extends ConnectionMiddleware {

    public boolean verify_and_redirect(MiddlewareActivity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        boolean isConnected = cm.isConnected();
        if(isConnected){
            redirect(activity);
            return true;
        }
        return false;
    }

    public void redirect(MiddlewareActivity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        User user = cm.getUtilisateur();
        activity.finish();
        Intent intent;
        if(user.is_merchant){
            intent = new Intent(activity, MenuActivityM.class);
        } else {
            intent = new Intent(activity, MenuActivityC.class);
        }
        activity.setIsRedirected(true);
        activity.startActivity(intent);
    }
}
