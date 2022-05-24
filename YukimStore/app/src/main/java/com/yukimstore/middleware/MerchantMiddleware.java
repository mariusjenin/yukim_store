package com.yukimstore.middleware;

import android.app.Activity;
import android.content.Intent;

import com.yukimstore.activity.used.client.ClientMenuActivity;
import com.yukimstore.activity.used.merchant.MerchantMenuActivity;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur l'ecran d'identification
 * si l'utilisateur est pas un client
 */
public class MerchantMiddleware extends TypeUserMiddleware {
    public boolean verify_and_redirect(Activity activity){
        ConnectionManager cm = ConnectionManager.getInstance();
        if(!cm.getUtilisateur().is_merchant){
            redirect(activity);
            return true;
        }
        return false;
    }
    public void redirect(Activity activity){
        activity.finish();
        Intent intent = new Intent(activity, MerchantMenuActivity.class);
        activity.startActivity(intent);
    }
}
