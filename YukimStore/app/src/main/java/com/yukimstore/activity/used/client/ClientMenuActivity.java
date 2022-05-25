package com.yukimstore.activity.used.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;

public class ClientMenuActivity extends ConnectedClientActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_menu);
    }

    public void findProduct(View view) {
        Intent intent;
        intent = new Intent(this, FindProductActivity.class);
        this.startActivity(intent);;
    }

    public void prodInteresting(View view) {
        //TODO remplace getAll by all interesting for user connected
        Intent intent;
        intent = new Intent(this, ConsultListProductsActivity.class);
        intent.putExtra("title","All products");
        ArrayList<Product> products = (ArrayList<Product>) AppDatabase.getInstance(ClientMenuActivity.this).productDAO().getAll();
        intent.putExtra("products",products );
        this.startActivity(intent);
    }

    public void findStore(View view) {
        Intent intent;
        intent = new Intent(this, FindStoreActivity.class);
        this.startActivity(intent);
    }

    public void seeBasket(View view) {
        Intent intent;
        intent = new Intent(this, ConsultBasketActivity.class);
        this.startActivity(intent);
    }

    public void myOrders(View view) {
        Intent intent;
        intent = new Intent(this, ConsultOrdersActivity.class);
        this.startActivity(intent);
    }

    public void logOut(View view) {
        ConnectionManager cm = ConnectionManager.getInstance();
        cm.disconnect();
        cm.removeTokenUserFromPrefs(this);
        connection_middleware.redirect(this);
    }
}