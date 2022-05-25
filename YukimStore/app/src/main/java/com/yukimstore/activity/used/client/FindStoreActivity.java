package com.yukimstore.activity.used.client;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedClientActivity;
import com.yukimstore.adapter.client.StoreListAdapter;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Store;

import java.util.List;

public class FindStoreActivity extends ConnectedClientActivity {

    private ListView list_stores;
    private TextView no_result;
    private EditText store_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.c_find_store);


        list_stores = findViewById(R.id.list_stores);
        no_result  = findViewById(R.id.no_result);

        updateListStores("");

        store_name = findViewById(R.id.find_stores);
        store_name.addTextChangedListener(new TextWatcher() {
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
                updateListStores(editable.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListStores(store_name.getText().toString());
    }

    public void updateListStores(String str){
        List<Store> stores = AppDatabase.getInstance(FindStoreActivity.this).storeDAO().getStoresLike(str);
        StoreListAdapter customAdapter = new StoreListAdapter(this, stores);
        list_stores.setAdapter(customAdapter);
        if(stores.size()>0){
            no_result.setVisibility(View.GONE);
        } else {
            no_result.setVisibility(View.VISIBLE);
        }
    }
}