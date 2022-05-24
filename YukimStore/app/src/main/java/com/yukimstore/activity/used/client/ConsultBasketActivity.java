package com.yukimstore.activity.used.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.ProductInBasketAdapter;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

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
        total_price.setText(String.valueOf(AppDatabase.getInstance(ConsultBasketActivity.this).productInBasketDAO().getSumBasket(user.id_user)) + getResources().getString(R.string.euro));
    }
}