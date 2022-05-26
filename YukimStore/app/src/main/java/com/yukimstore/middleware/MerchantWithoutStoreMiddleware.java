package com.yukimstore.middleware;

import android.app.Activity;
import android.content.Intent;

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
    public boolean verify_and_redirect(Activity activity){
        ConnectionManager cm = ConnectionManager.getInstance();

        User user = cm.getUtilisateur();
        Store store = AppDatabase.getInstance(activity).storeDAO().get(user.id_user);
        if(store!=null){
            redirect(activity);
            return true;
        }
        return false;
    }
    public void redirect(Activity activity){
        activity.finish();
        Intent intent = new Intent(activity, MenuActivityM.class);
        activity.startActivity(intent);
    }
}
