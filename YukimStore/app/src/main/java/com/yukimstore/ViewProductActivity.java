package com.yukimstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewProductActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product);

        db = AppDatabase.getInstance(getApplicationContext());

        Product product = db.productDAO().get(0);

        TextView et_name = (TextView)findViewById(R.id.vp_name);
        et_name.setText(product.name);

        TextView et_details = (TextView)findViewById(R.id.vp_details);
        et_details.setText(product.details);

        //TextView et_price = (TextView)findViewById(R.id.vp_price);
        //et_price.setText(product.price);
    }
}