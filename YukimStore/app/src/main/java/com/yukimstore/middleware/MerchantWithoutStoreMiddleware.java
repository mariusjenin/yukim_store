package com.yukimstore.middleware;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.yukimstore.activity.unused.MiddlewareActivity;
import com.yukimstore.activity.used.merchant.MenuActivityM;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

/**
 * Middleware permettant de rediriger l'application sur le menu
 * si l'utilisateur marchand a un store
 */
public class MerchantWithoutStoreMiddleware extends StoreMiddleWare {
    public boolean verify_and_redirect(MiddlewareActivity activity){
        ConnectionManager cm = ConnectionManager.getInstance();

        User user = cm.getUtilisateur();
        Store store = AppDatabase.getInstance(activity).storeDAO().get(user.id_user);
        if(store != null){
            redirect(activity);
            return true;
        }
        return false;
    }
    @SuppressLint("LongLogTag")
    public void redirect(MiddlewareActivity activity){
        activity.finish();
        Intent intent = new Intent(activity, MenuActivityM.class);
        activity.setIsRedirected(true);
        activity.startActivity(intent);
    }
}
