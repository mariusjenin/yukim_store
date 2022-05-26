package com.yukimstore.activity.concrete_activity.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yukimstore.R;
import com.yukimstore.activity.abstract_activity.ConnectedMerchantWithoutStoreActivity;
import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.manager.ConnectionManager;

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

    public void createStore(View view) {
        EditText storeNameInput = findViewById(R.id.storeName);
        String storeName = storeNameInput.getText().toString();

        if(storeName.isEmpty()) {
            Toast.makeText(this,"Please enter a valid name",Toast.LENGTH_SHORT).show();
            return;
        }

        ConnectionManager cm = ConnectionManager.getInstance();
        User user = cm.getUtilisateur();

        Store store = new Store(user.id_user,storeName,null);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        db.storeDAO().insert(store);

        switch(choice) {
            case CLOTHING: {
                generateClothingTemplate(db,user.id_user);
                break;
            }
            case SPORT: {
                generateSportTemplate(db,user.id_user);
                break;
            }
            case FOOD: {
                generateFoodTemplate(db,user.id_user);
                break;
            }
            case SELFCARE: {
                generateSelfcareTemplate(db,user.id_user);
                break;
            }
            case MUSIC: {
                generateMusicTemplate(db,user.id_user);
                break;
            }
            case TECHNOLOGY: {
                generateTechnologyTemplate(db,user.id_user);
                break;
            }
        }

        Toast.makeText(this,"Your store is open !",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MenuActivityM.class));
    }

    private void generateClothingTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Tops",user_id));
        categories.add(new Category("Shirts",user_id));
        categories.add(new Category("Sweaters",user_id));
        categories.add(new Category("Dresses",user_id));
        categories.add(new Category("Jeans",user_id));
        categories.add(new Category("Pants",user_id));
        categories.add(new Category("Shorts",user_id));
        categories.add(new Category("Skirts",user_id));
        categories.add(new Category("Coats",user_id));
        categories.add(new Category("Suits",user_id));
        categories.add(new Category("Underwear",user_id));
        categories.add(new Category("Swimwear",user_id));

        db.categoryDAO().insertAll(categories);
    }

    private void generateSportTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Athletics",user_id));
        categories.add(new Category("Baseball",user_id));
        categories.add(new Category("Basketball",user_id));
        categories.add(new Category("Climbing",user_id));
        categories.add(new Category("Fitness",user_id));
        categories.add(new Category("Football",user_id));
        categories.add(new Category("Golf",user_id));
        categories.add(new Category("Karate",user_id));
        categories.add(new Category("Skateboard",user_id));
        categories.add(new Category("Swimming",user_id));
        categories.add(new Category("Tennis",user_id));
        categories.add(new Category("Yoga",user_id));

        db.categoryDAO().insertAll(categories);
    }

    private void generateFoodTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bakery",user_id));
        categories.add(new Category("Breakfast",user_id));
        categories.add(new Category("Meat",user_id));
        categories.add(new Category("Seafood",user_id));
        categories.add(new Category("Fruits & Vegetables",user_id));
        categories.add(new Category("Dairy",user_id));
        categories.add(new Category("Pantry",user_id));
        categories.add(new Category("Candy",user_id));
        categories.add(new Category("Frozen",user_id));
        categories.add(new Category("Beverage",user_id));

        db.categoryDAO().insertAll(categories);
    }

    private void generateSelfcareTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bath & Body",user_id));
        categories.add(new Category("Hair care",user_id));
        categories.add(new Category("Shave",user_id));
        categories.add(new Category("Sun care & Tanning",user_id));
        categories.add(new Category("Relaxation",user_id));

        db.categoryDAO().insertAll(categories);
    }

    private void generateMusicTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Guitars",user_id));
        categories.add(new Category("Basses",user_id));
        categories.add(new Category("Drums",user_id));
        categories.add(new Category("Keys",user_id));
        categories.add(new Category("Strings",user_id));
        categories.add(new Category("Winds",user_id));

        db.categoryDAO().insertAll(categories);
    }

    private void generateTechnologyTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Computers",user_id));
        categories.add(new Category("Video Games",user_id));
        categories.add(new Category("Cell-phones",user_id));
        categories.add(new Category("TV",user_id));
        categories.add(new Category("Cameras",user_id));
        categories.add(new Category("Home Studio",user_id));
        categories.add(new Category("Wearables",user_id));

        db.categoryDAO().insertAll(categories);
    }
}
