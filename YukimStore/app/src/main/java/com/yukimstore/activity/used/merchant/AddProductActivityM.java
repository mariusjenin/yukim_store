package com.yukimstore.activity.used.merchant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.used.ConnectedMerchantWithStoreActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.Product;

import java.util.List;

public class AddProductActivityM extends ConnectedMerchantWithStoreActivity {
    private Category category;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        setContentView(R.layout.m_add_product);
        List<Interest> interests = AppDatabase.getInstance(this).interestDAO().getAll();

        category = (Category) getIntent().getSerializableExtra("category");
        TextView title = findViewById(R.id.title_add_product);
        title.setText(getResources().getString(R.string.new_product_in)+category.name);
        Button validate = findViewById(R.id.validate);
        TextView product_name = findViewById(R.id.product_name);
        TextView details_product = findViewById(R.id.details_product);
        TextView price_product = findViewById(R.id.price_product);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_name_str = product_name.getText().toString();
                String details_product_str = details_product.getText().toString();
                float price_product_float = Float.parseFloat(price_product.getText().toString());

                if(product_name_str.isEmpty()) {
                    Toast.makeText(AddProductActivityM.this,"Please enter a valid product name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(price_product_float<=0) {
                    Toast.makeText(AddProductActivityM.this,"Please enter a valid product price",Toast.LENGTH_SHORT).show();
                    return;
                }

                Product product = new Product(product_name_str,details_product_str,price_product_float,category.id_category,null);
                AppDatabase.getInstance(AddProductActivityM.this).productDAO().insert(product);

                finish();
            }
        });
    }
}
