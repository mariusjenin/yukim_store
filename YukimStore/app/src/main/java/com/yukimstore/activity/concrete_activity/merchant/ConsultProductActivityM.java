package com.yukimstore.activity.concrete_activity.merchant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.DateConverter;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Offer;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ConsultProductActivityM extends ConnectedMerchantWithStoreActivity {
    private Product product;
    private Category category;

    private TextView category_name;
    private TextView product_name;
    private TextView price_product;
    private TextView details_product;
    private TextView offer_price;
    private ImageView img_product;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        setContentView(R.layout.m_consult_product);
        AppDatabase apd = AppDatabase.getInstance(this);
        product = (Product) getIntent().getSerializableExtra("product");
        category = apd.categoryDAO().get(product.id_category);

        img_product = findViewById(R.id.img_product2);
        category_name = findViewById(R.id.category_name);
        product_name = findViewById(R.id.product_name);
        price_product = findViewById(R.id.product_price);
        details_product = findViewById(R.id.product_details);
        offer_price = findViewById(R.id.offer_price);
        Button add_replace_offer = findViewById(R.id.add_replace_offer);

        add_replace_offer.setText(getResources().getString(R.string.add_special_offer));
        add_replace_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ConsultProductActivityM.this, AddOfferActivityM.class);
                intent.putExtra("product",product);
                startActivity(intent);
            }
        });

        update();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @SuppressLint("SetTextI18n")
    private void update(){
        product = AppDatabase.getInstance(this).productDAO().get(product.id_product);

        product_name.setText(product.name);
        category_name.setText(category.name);
        price_product.setText(String.valueOf(product.price)+getResources().getString(R.string.euro));
        details_product.setText(product.details);

        Offer offer = AppDatabase.getInstance(this).offerDAO().get(product.id_product,new Date());
        if(offer== null ){
            offer_price.setVisibility(View.GONE);
        } else {
            offer_price.setVisibility(View.VISIBLE);
            offer_price.setText(getResources().getString(R.string.actual_offer_price) + offer.price +getResources().getString(R.string.euro));
        }
        Bitmap bmp;
        if (product.img != null) {
            bmp = BitmapFactory.decodeByteArray(product.img, 0, product.img.length);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.product);
        }
        img_product.setImageBitmap(bmp);
    }
}
