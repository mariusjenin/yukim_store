package com.yukimstore.activity.used.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.activity.ConnectedMerchantActivity;

public class MerchantMenuActivity extends ConnectedMerchantActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_menu);
    }

    public void merchantOrders(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void merchantCategories(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void merchantProducts(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }
}