package com.yukimstore.activity.concrete_activity.merchant;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

public class ConsultProductActivityM extends ConnectedMerchantWithStoreActivity {
    Product product;
    Category category;
    Store store;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        setContentView(R.layout.m_consult_product);
        AppDatabase apd = AppDatabase.getInstance(this);
        product = (Product) getIntent().getSerializableExtra("product");
        category = apd.categoryDAO().get(product.id_category);
        store = apd.storeDAO().get(category.id_store);

        ImageView img_product = findViewById(R.id.img_product2);
        TextView category_name = findViewById(R.id.category_name);
        TextView product_name = findViewById(R.id.product_name);
        TextView price_product = findViewById(R.id.price_product2);
        TextView details_product = findViewById(R.id.product_details2);
        User user = ConnectionManager.getInstance().getUtilisateur();

        product_name.setText(product.name);
        category_name.setText(category.name);
        price_product.setText(String.valueOf(product.price)+getResources().getString(R.string.euro));
        details_product.setText(product.details);

        Bitmap bmp;
        if (product.img != null) {
            bmp = BitmapFactory.decodeByteArray(product.img, 0, product.img.length);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.product);
        }
        img_product.setImageBitmap(bmp);
    }
}
