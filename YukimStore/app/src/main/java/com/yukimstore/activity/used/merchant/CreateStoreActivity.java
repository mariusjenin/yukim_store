package com.yukimstore.activity.used.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.activity.ConnectedMerchantActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;

public class CreateStoreActivity extends ConnectedMerchantActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_create_store_2);
        db = AppDatabase.getInstance(getApplicationContext());
    }

    public void createStore(View view) {
        EditText et_pname = (EditText)findViewById(R.id.mcs2_productname);
        EditText et_pdetails = (EditText)findViewById(R.id.mcs2_productdetails);
        EditText et_pprice = (EditText)findViewById(R.id.mcs2_productprice);

        String pname = et_pname.getText().toString();
        String pdetails = et_pdetails.getText().toString();
        int pprice = Integer.parseInt(et_pprice.getText().toString());

        if(pname.equals("") || pdetails.equals("") || pprice <= 0) {
            Toast.makeText(this,"Please give coherent values for the product",Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(pname,pdetails,pprice,0,null); //TODO

        db.productDAO().insert(product);

        startActivity(new Intent(this,MerchantMenuActivity.class));
    }
}