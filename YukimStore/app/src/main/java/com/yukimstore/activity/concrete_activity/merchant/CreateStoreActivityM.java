package com.yukimstore.activity.concrete_activity.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithoutStoreActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.InterestForCategory;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;
import com.yukimstore.manager.TemplateManager;

import java.util.ArrayList;

public class CreateStoreActivityM extends ConnectedMerchantWithoutStoreActivity {
    private ImageView templateChoice;
    private Choice choice;

    private enum Choice { CLOTHING, SPORT, FOOD, SELFCARE, MUSIC, TECHNOLOGY }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_redirected) return;
        setContentView(R.layout.m_create_store);
        templateChoice = findViewById(R.id.TemplateChoice);
        choice = Choice.CLOTHING;
        FloatingActionButton leave_btn = findViewById(R.id.leave_btn);
        leave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectionManager cm = ConnectionManager.getInstance();
                cm.disconnect();
                cm.removeTokenUserFromPrefs(CreateStoreActivityM.this);
                connection_middleware.redirect(CreateStoreActivityM.this);
            }
        });

        Button create_my_store = findViewById(R.id.create_my_store);
        create_my_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStore();
            }
        });
    }

    public void setTemplateToClothing(View view) {
        templateChoice.setImageResource(R.drawable.template_clothing);
        choice = Choice.CLOTHING;
    }

    public void setTemplateToSport(View view) {
        templateChoice.setImageResource(R.drawable.template_sport);
        choice = Choice.SPORT;
    }

    public void setTemplateToFood(View view) {
        templateChoice.setImageResource(R.drawable.template_food);
        choice = Choice.FOOD;
    }

    public void setTemplateToSelfcare(View view) {
        templateChoice.setImageResource(R.drawable.template_selfcare);
        choice = Choice.SELFCARE;
    }

    public void setTemplateToMusic(View view) {
        templateChoice.setImageResource(R.drawable.template_music);
        choice = Choice.MUSIC;
    }

    public void setTemplateToTechnology(View view) {
        templateChoice.setImageResource(R.drawable.template_technology);
        choice = Choice.TECHNOLOGY;
    }

    public void createStore() {
        EditText storeNameInput = findViewById(R.id.storeName);
        String storeName = storeNameInput.getText().toString();

        if(storeName.isEmpty()) {
            Toast.makeText(this,"Please enter a valid name",Toast.LENGTH_SHORT).show();
            return;
        }

        ConnectionManager cm = ConnectionManager.getInstance();
        User user = cm.getUtilisateur();

        Log.e("",""+user.id_user);

        Store store = new Store(user.id_user,storeName,null);

        Log.e("",""+store.id_user_store);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        db.storeDAO().insert(store);

        switch(choice) {
            case CLOTHING: {
                TemplateManager.getInstance().generateClothingTemplate(db,user.id_user);
                break;
            }
            case SPORT: {
                TemplateManager.getInstance().generateSportTemplate(db,user.id_user);
                break;
            }
            case FOOD: {
                TemplateManager.getInstance().generateFoodTemplate(db,user.id_user);
                break;
            }
            case SELFCARE: {
                TemplateManager.getInstance().generateSelfcareTemplate(db,user.id_user);
                break;
            }
            case MUSIC: {
                TemplateManager.getInstance().generateMusicTemplate(db,user.id_user);
                break;
            }
            case TECHNOLOGY: {
                TemplateManager.getInstance().generateTechnologyTemplate(db,user.id_user);
                break;
            }
        }

        Toast.makeText(this,"Your store is open !",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MenuActivityM.class));
    }
}
