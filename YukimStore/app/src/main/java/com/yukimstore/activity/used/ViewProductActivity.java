package com.yukimstore.activity.used;

import android.os.Bundle;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.activity.YukimActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;

public class ViewProductActivity extends ConnectedActivity {

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