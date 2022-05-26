package com.yukimstore.activity.used.merchant;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.adapter.merchant.ProductListAdapterM;
import com.yukimstore.db.entity.Product;

import java.util.List;

public class ConsultListProductsActivityM extends ConnectedMerchantWithStoreActivity {
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.m_list_products);

        products = (List<Product>) getIntent().getSerializableExtra("products");
        String title = getIntent().getStringExtra("title");

        ListView list_products = findViewById(R.id.list_products);
        TextView title_consult_products = findViewById(R.id.title_consult_products);
        TextView no_result  = findViewById(R.id.no_result);

        title_consult_products.setText(title);

        if(products.size()>0){
            ProductListAdapterM customAdapter = new ProductListAdapterM(this, products);
            list_products.setAdapter(customAdapter);
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}
