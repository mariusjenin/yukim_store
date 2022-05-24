package com.yukimstore.activity.used.client;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.adapter.ClientProductListAdapter;
import com.yukimstore.adapter.StoreListAdapter;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.util.List;

public class FindProductActivity extends ConnectedActivity {

    private ListView list_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.c_find_product);


        list_products = findViewById(R.id.list_products);
        updateListStores("");

        EditText product_name = findViewById(R.id.find_products);
        product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateListStores(editable.toString());
            }
        });
    }

    public void updateListStores(String str){
        List<Product> products = AppDatabase.getInstance(FindProductActivity.this).productDAO().getProductsLike(str);
        ClientProductListAdapter customAdapter = new ClientProductListAdapter(this, R.layout.c_product_item, products);
        list_products.setAdapter(customAdapter);
    }
}
