package com.yukimstore.activity.used.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.ProductInBasketAdapter;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.ProductInOrder;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConsultBasketActivity extends ConnectedClientActivity {

    private TextView no_result;
    private ListView list_products_basket;
    TextView total_price;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.c_consult_basket);

        user = ConnectionManager.getInstance().getUtilisateur();

        list_products_basket = findViewById(R.id.list_products_basket);
        no_result  = findViewById(R.id.no_result);
        total_price = findViewById(R.id.total_price);
        Button clear_basket = findViewById(R.id.clear_basket);
        Button validate_basket = findViewById(R.id.validate_basket);

        clear_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.getInstance(ConsultBasketActivity.this).productInBasketDAO().clearBasket(user.id_user);
                updateBasket();
            }
        });

        validate_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase apd = AppDatabase.getInstance(ConsultBasketActivity.this);
                Date date = new Date();
                //Get all stores concerned
                List<Store> stores = apd.productInBasketDAO().getStoreWithProductsInBasket(user.id_user);
                int size_stores = stores.size();
                for(int i = 0; i < size_stores;i++){
                    //One order by store
                    Order o = new Order(stores.get(i).id_user_store,user.id_user,date);
                    int id_order = (int) apd.orderDAO().insert(o);
                    //Get all products of this store
                    List<ProductInBasket> pibs = apd.productInBasketDAO().getWithStoreAndUser(stores.get(i).id_user_store,user.id_user);

                    int size_pibs = pibs.size();
                    for(int j = 0; j < size_pibs;j++){
                        ProductInOrder pio = new ProductInOrder(id_order,pibs.get(j).id_product,pibs.get(j).quantity);
                        apd.productInOrderDAO().insert(pio);
                    }
                }
                apd.productInBasketDAO().clearBasket(user.id_user);


                Intent intent;
                intent = new Intent(ConsultBasketActivity.this, ConsultOrdersActivity.class);
                startActivity(intent);
            }
        });

        updateBasket();
    }



    @Override
    protected void onResume() {
        super.onResume();
        updateBasket();
    }

    @SuppressLint("SetTextI18n")
    public void updateBasket(){
        List<ProductInBasket> pibs = AppDatabase.getInstance(ConsultBasketActivity.this).productInBasketDAO().getWithUser(user.id_user);
        ProductInBasketAdapter customAdapter = new ProductInBasketAdapter(this, pibs);
        list_products_basket.setAdapter(customAdapter);
        if(pibs.size()>0){
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
        total_price.setText(AppDatabase.getInstance(ConsultBasketActivity.this).productInBasketDAO().getSumBasket(user.id_user) + getResources().getString(R.string.euro));
    }
}