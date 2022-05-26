package com.yukimstore.activity.used.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.activity.used.IdentificationActivity;
import com.yukimstore.manager.ConnectionManager;

public class MerchantMenuActivityM extends ConnectedMerchantWithStoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_menu);
    }

    public void disconnect(View view) {
        ConnectionManager cm = ConnectionManager.getInstance();
        cm.disconnect();
        cm.removeTokenUserFromPrefs(this);
        startActivity(new Intent(this, IdentificationActivity.class));
    }

    public void myCategories(View view) {
        startActivity(new Intent(this,ConsultCategoriesStoreActivityM.class));
    }

    public void myProducts(View view) {

    }

    public void orders(View view) {

    }
}