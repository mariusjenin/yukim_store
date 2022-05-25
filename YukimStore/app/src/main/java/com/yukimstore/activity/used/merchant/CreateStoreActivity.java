package com.yukimstore.activity.used.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yukimstore.R;
import com.yukimstore.activity.ConnectedActivity;
import com.yukimstore.activity.ConnectedMerchantActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

public class CreateStoreActivity extends ConnectedMerchantActivity {
    private ImageView templateChoice;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_create_store);
        templateChoice = findViewById(R.id.TemplateChoice);
        choice = "clothing";
    }

    public void setTemplateToClothing(View view) {
        templateChoice.setImageResource(R.drawable.template_clothing);
        choice = "clothing";
    }

    public void setTemplateToSport(View view) {
        templateChoice.setImageResource(R.drawable.template_sport);
        choice = "sport";
    }

    public void setTemplateToFood(View view) {
        templateChoice.setImageResource(R.drawable.template_food);
        choice = "food";
    }

    public void setTemplateToSelfcare(View view) {
        templateChoice.setImageResource(R.drawable.template_selfcare);
        choice = "selfcare";
    }

    public void setTemplateToMusic(View view) {
        templateChoice.setImageResource(R.drawable.template_music);
        choice = "music";
    }

    public void setTemplateToTechnology(View view) {
        templateChoice.setImageResource(R.drawable.template_technology);
        choice = "technology";
    }

    public void createStore(View view) {
        EditText storeNameInput = findViewById(R.id.storeName);
        String storeName = storeNameInput.getText().toString();

        if(storeName.equals("")) {
            Toast.makeText(this,"Please enter a valid name",Toast.LENGTH_SHORT).show();
            return;
        }

        ConnectionManager cm = ConnectionManager.getInstance();
        User user = cm.getUtilisateur();

        Store store = new Store(user.id_user,storeName,null);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        db.storeDAO().insert(store);

        // TODO : Gérer les templates

        startActivity(new Intent(this,MerchantMenuActivity.class));
    }
}