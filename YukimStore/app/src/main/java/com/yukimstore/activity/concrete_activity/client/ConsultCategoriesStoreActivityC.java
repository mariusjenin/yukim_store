package com.yukimstore.activity.concrete_activity.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.CategoryListAdapterC;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Store;

import java.util.List;

public class ConsultCategoriesStoreActivityC extends ConnectedClientActivity {
    Store store;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        this.setContentView(R.layout.c_consult_categories_store);
        store = (Store) getIntent().getSerializableExtra("store");

        ListView list_categories = findViewById(R.id.list_categories);
        TextView title_consult_categories = findViewById(R.id.categories_of_title);
        TextView no_result  = findViewById(R.id.no_result);

        title_consult_categories.setText(getResources().getString(R.string.categories_of) + store.name);

        List<Category> categories = AppDatabase.getInstance(ConsultCategoriesStoreActivityC.this).categoryDAO().getCategoriesWithStore(store.id_user_store);

        if(categories.size()>0){
            CategoryListAdapterC customAdapter = new CategoryListAdapterC(this, categories);
            list_categories.setAdapter(customAdapter);
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}
