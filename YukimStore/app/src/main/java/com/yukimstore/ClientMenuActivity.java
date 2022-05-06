package com.yukimstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ClientMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_menu);
    }

    public void goBasket(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void goMyOrders(View view) {
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }

    public void goProducts(View view) {
        startActivity(new Intent(this,ViewProductActivity.class));
    }
}