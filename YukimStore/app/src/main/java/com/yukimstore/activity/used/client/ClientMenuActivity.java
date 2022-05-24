package com.yukimstore.activity.used.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.activity.YukimActivity;
import com.yukimstore.activity.used.merchant.MerchantMenuActivity;

public class ClientMenuActivity extends ConnectedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_menu);
    }

    public void findProduct(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void prodInteresting(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void findStore(View view) {
        Intent intent;
        intent = new Intent(this, FindStoreActivity.class);
        this.startActivity(intent);
    }

    public void seeBasket(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void myOrders(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }
}