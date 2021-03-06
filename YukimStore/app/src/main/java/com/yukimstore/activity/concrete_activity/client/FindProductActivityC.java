package com.yukimstore.activity.concrete_activity.client;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.ProductListAdapterC;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;

import java.util.List;

public class FindProductActivityC extends ConnectedClientActivity {

    private ListView list_products;
    private TextView no_result;
    private EditText product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        this.setContentView(R.layout.c_find_product);


        list_products = findViewById(R.id.list_products);
        no_result  = findViewById(R.id.no_result);

        updateListProducts("");

        product_name = findViewById(R.id.find_products);
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
                updateListProducts(editable.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListProducts(product_name.getText().toString());
    }

    public void updateListProducts(String str){
        List<Product> products = AppDatabase.getInstance(FindProductActivityC.this).productDAO().getProductsLike(str);
        ProductListAdapterC customAdapter = new ProductListAdapterC(this, products);
        list_products.setAdapter(customAdapter);
        if(products.size()>0){
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}
