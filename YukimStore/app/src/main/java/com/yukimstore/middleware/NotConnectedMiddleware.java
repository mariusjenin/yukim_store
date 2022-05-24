package com.yukimstore.middleware;

import android.app.Activity;
import android.content.Intent;

import com.yukimstore.activity.used.client.ClientMenuActivity;
import com.yukimstore.activity.used.merchant.MerchantMenuActivity;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur l'ecran d'accueil
 * si l'utilisateur est connecté
 */
public class NotConnectedMiddleware implements YukimMiddleware {

    public void verify_and_redirect(Activity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        boolean isConnected = cm.isConnected();
        if(isConnected){
            redirect(activity);
        }
    }

    public void redirect(Activity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        User user = cm.getUtilisateur();
        activity.finish();
        Intent intent;
        if(user.is_merchant){
            intent = new Intent(activity, MerchantMenuActivity.class);
        } else {
            intent = new Intent(activity, ClientMenuActivity.class);
        }
        activity.startActivity(intent);
    }
}
