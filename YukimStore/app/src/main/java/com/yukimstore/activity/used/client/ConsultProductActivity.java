package com.yukimstore.activity.used.client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.util.ArrayList;

public class ConsultProductActivity extends ConnectedClientActivity {
    Product product;
    Category category;
    Store store;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_consult_product);
        AppDatabase apd = AppDatabase.getInstance(this);
        product = (Product) getIntent().getSerializableExtra("product");
        category = apd.categoryDAO().get(product.id_category);
        store = apd.storeDAO().get(category.id_store);

        Button consult_store = findViewById(R.id.consult_store);
        Button add_to_basket = findViewById(R.id.add_to_basket);
        ImageView img_product = findViewById(R.id.img_product);
        TextView store_name = findViewById(R.id.store_name);
        TextView category_name = findViewById(R.id.category_name);
        TextView product_name = findViewById(R.id.product_name);
        TextView price_product = findViewById(R.id.price_product);
        TextView details_product = findViewById(R.id.product_details);

        product_name.setText(product.name);
        category_name.setText(category.name);
        store_name.setText(store.name);
        price_product.setText(String.valueOf(product.price)+getResources().getString(R.string.euro));
        details_product.setText(product.details);
        consult_store.setText(getResources().getString(R.string.consult) + store.name);

        Bitmap bmp;
        if (product.img != null) {
            bmp = BitmapFactory.decodeByteArray(product.img, 0, product.img.length);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.product);
        }
        img_product.setImageBitmap(bmp);

        consult_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ConsultProductActivity.this, ConsultStoreActivity.class);
                intent.putExtra("store",store);
                ConsultProductActivity.this.startActivity(intent);
            }
        });

        add_to_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConsultProductActivity.this,"Not yet implemented",Toast.LENGTH_SHORT).show();
            }
        });

    }

}