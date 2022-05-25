package com.yukimstore.activity.used.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.ProductInOrderAdapterC;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultOrderActivityC extends ConnectedClientActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.c_consult_order);
        Order order = (Order) getIntent().getSerializableExtra("order");

        ListView list_products = findViewById(R.id.list_products);
        TextView title_consult_products = findViewById(R.id.title_consult_order);
        TextView date_order = findViewById(R.id.date_order);
        TextView total_price_order = findViewById(R.id.total_price_order);
        TextView no_result  = findViewById(R.id.no_result);

        total_price_order.setText(AppDatabase.getInstance(this).productInOrderDAO().getSumOrder(order.id_order)+getResources().getString(R.string.euro));

        Store s = AppDatabase.getInstance(this).storeDAO().get(order.id_store);
        title_consult_products.setText(getResources().getString(R.string.order_at)+s.name);

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_str = dateFormat.format(order.date_order);
        date_order.setText(date_str);

        List<Product> products = AppDatabase.getInstance(this).productInOrderDAO().getProductsWithOrder(order.id_order);

        if(products.size()>0){
            ProductInOrderAdapterC customAdapter = new ProductInOrderAdapterC(this, products,order);
            list_products.setAdapter(customAdapter);
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}