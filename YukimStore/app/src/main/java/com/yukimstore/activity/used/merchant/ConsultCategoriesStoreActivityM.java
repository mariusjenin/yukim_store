package com.yukimstore.activity.used.merchant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.activity.ConnectedMerchantWithStoreActivity;
import com.yukimstore.adapter.client.CategoryListAdapterC;
import com.yukimstore.adapter.merchant.CategoryListAdapterM;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.manager.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class ConsultCategoriesStoreActivityM extends ConnectedMerchantWithStoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_consult_categories_store);

        ConnectionManager cm = ConnectionManager.getInstance();
        int user_id = cm.getUtilisateur().id_user;
        List<Category> categories = AppDatabase.getInstance(this).categoryDAO().getCategoriesWithStore(user_id);

        ListView list_categories = findViewById(R.id.list_categories);
        TextView no_result  = findViewById(R.id.no_result);
        Button add_category = findViewById(R.id.add_category);

        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ConsultCategoriesStoreActivityM.this, AddCategoryActivityM.class);
                startActivity(intent);

//                Toast.makeText(ConsultCategoriesStoreActivityM.this,"Not yet implemented",Toast.LENGTH_SHORT).show();
            }
        });

        if(categories.size()>0){
            CategoryListAdapterM customAdapter = new CategoryListAdapterM(this, categories);
            list_categories.setAdapter(customAdapter);
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}
