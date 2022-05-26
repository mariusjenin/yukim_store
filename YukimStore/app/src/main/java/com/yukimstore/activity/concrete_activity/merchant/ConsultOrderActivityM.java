package com.yukimstore.activity.concrete_activity.merchant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedClientActivity;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.adapter.client.ProductInOrderAdapterC;
import com.yukimstore.adapter.merchant.ProductInOrderAdapterM;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultOrderActivityM extends ConnectedMerchantWithStoreActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (is_redirected) return;
        this.setContentView(R.layout.m_consult_order);

        Order order = (Order) getIntent().getSerializableExtra("order");

        ListView list_products = findViewById(R.id.list_products);
        TextView title_consult_products = findViewById(R.id.title_consult_order);
        TextView date_order = findViewById(R.id.date_order);
        TextView total_price_order = findViewById(R.id.total_price_order);
        TextView no_result = findViewById(R.id.no_result);

        float price = Math.round(AppDatabase.getInstance(this).productInOrderDAO().getSumOrder(order.id_order) * 100.0f) / 100.0f;
        total_price_order.setText(price + getResources().getString(R.string.euro));

        User u = AppDatabase.getInstance(this).userDAO().get(order.id_user);

        title_consult_products.setText(u.first_name + " " + u.last_name);

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_str = dateFormat.format(order.date_order);
        date_order.setText(date_str);

        List<Product> products = AppDatabase.getInstance(this).productInOrderDAO().getProductsWithOrder(order.id_order);

        if (products.size() > 0) {
            ProductInOrderAdapterM customAdapter = new ProductInOrderAdapterM(this, products, order);
            list_products.setAdapter(customAdapter);
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}

