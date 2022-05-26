package com.yukimstore.activity.used.client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;

public class MenuActivityC extends ConnectedClientActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_menu);
        TextView name_user = findViewById(R.id.name_user);
        User user = ConnectionManager.getInstance().getUtilisateur();
        name_user.setText(getResources().getString(R.string.hi)+user.first_name+" "+user.last_name+getResources().getString(R.string.exclamation_mark));
    }

    public void findProduct(View view) {
        Intent intent;
        intent = new Intent(this, FindProductActivityC.class);
        this.startActivity(intent);;
    }

    public void prodInteresting(View view) {
        //TODO remplace getAll by all interesting for user connected
        Intent intent;
        intent = new Intent(this, ConsultListProductsActivityC.class);
        intent.putExtra("title","All products");
        ArrayList<Product> products = (ArrayList<Product>) AppDatabase.getInstance(MenuActivityC.this).productDAO().getAll();
        intent.putExtra("products",products );
        this.startActivity(intent);
    }

    public void findStore(View view) {
        Intent intent;
        intent = new Intent(this, FindStoreActivityC.class);
        this.startActivity(intent);
    }

    public void seeBasket(View view) {
        Intent intent;
        intent = new Intent(this, ConsultBasketActivityC.class);
        this.startActivity(intent);
    }

    public void myOrders(View view) {
        Intent intent;
        intent = new Intent(this, ConsultOrdersActivityC.class);
        this.startActivity(intent);
    }

    public void logOut(View view) {
        ConnectionManager cm = ConnectionManager.getInstance();
        cm.disconnect();
        cm.removeTokenUserFromPrefs(this);
        connection_middleware.redirect(this);
    }
}