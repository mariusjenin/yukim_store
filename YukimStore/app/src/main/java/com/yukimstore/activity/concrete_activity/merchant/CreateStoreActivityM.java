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

        Interest interest_top_garment = db.interestDAO().get("Top garments");
        Interest interest_bot_garment = db.interestDAO().get("Bottom garments");
        Category category_tops = db.categoryDAO().getWithStoreAndName(user_id,"Tops");
        Category category_shirts = db.categoryDAO().getWithStoreAndName(user_id,"Shirts");
        Category category_sweaters = db.categoryDAO().getWithStoreAndName(user_id,"Sweaters");
        Category category_dresses = db.categoryDAO().getWithStoreAndName(user_id,"Dresses");
        Category category_jeans = db.categoryDAO().getWithStoreAndName(user_id,"Jeans");
        Category category_pants = db.categoryDAO().getWithStoreAndName(user_id,"Pants");
        Category category_shorts = db.categoryDAO().getWithStoreAndName(user_id,"Shorts");
        Category category_skirts = db.categoryDAO().getWithStoreAndName(user_id,"Skirts");
        Category category_coats = db.categoryDAO().getWithStoreAndName(user_id,"Coats");
        Category category_suits = db.categoryDAO().getWithStoreAndName(user_id,"Suits");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_tops.id_category));
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_shirts.id_category));
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_sweaters.id_category));
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_dresses.id_category));
        ifcs.add(new InterestForCategory(interest_bot_garment.id_interest,category_jeans.id_category));
        ifcs.add(new InterestForCategory(interest_bot_garment.id_interest,category_pants.id_category));
        ifcs.add(new InterestForCategory(interest_bot_garment.id_interest,category_shorts.id_category));
        ifcs.add(new InterestForCategory(interest_bot_garment.id_interest,category_skirts.id_category));
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_coats.id_category));
        ifcs.add(new InterestForCategory(interest_top_garment.id_interest,category_suits.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
    }

    private void generateSportTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Athletics",user_id));
        categories.add(new Category("Basketball",user_id));
        categories.add(new Category("Climbing",user_id));
        categories.add(new Category("Football",user_id));
        categories.add(new Category("Golf",user_id));
        categories.add(new Category("Karate",user_id));
        categories.add(new Category("Swimming",user_id));
        categories.add(new Category("Tennis",user_id));
        categories.add(new Category("Paragliding",user_id));

        db.categoryDAO().insertAll(categories);

        Interest interest_classical_sport = db.interestDAO().get("Classical sports");
        Interest interest_extreme_sport = db.interestDAO().get("Extreme sports");

        Category category_athletics = db.categoryDAO().getWithStoreAndName(user_id,"Athletics");
        Category category_basketbal = db.categoryDAO().getWithStoreAndName(user_id,"Basketball");
        Category category_climbing = db.categoryDAO().getWithStoreAndName(user_id,"Climbing");
        Category category_football = db.categoryDAO().getWithStoreAndName(user_id,"Football");
        Category category_golf = db.categoryDAO().getWithStoreAndName(user_id,"Golf");
        Category category_karate = db.categoryDAO().getWithStoreAndName(user_id,"Karate");
        Category category_swimming = db.categoryDAO().getWithStoreAndName(user_id,"Swimming");
        Category category_tennis = db.categoryDAO().getWithStoreAndName(user_id,"Tennis");
        Category category_paragliding = db.categoryDAO().getWithStoreAndName(user_id,"Paragliding");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_athletics.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_basketbal.id_category));
        ifcs.add(new InterestForCategory(interest_extreme_sport.id_interest,category_climbing.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_football.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_golf.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_karate.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_swimming.id_category));
        ifcs.add(new InterestForCategory(interest_classical_sport.id_interest,category_tennis.id_category));
        ifcs.add(new InterestForCategory(interest_extreme_sport.id_interest,category_paragliding.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
    }

    private void generateFoodTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bakery",user_id));
        categories.add(new Category("Breakfast",user_id));
        categories.add(new Category("Meat",user_id));
        categories.add(new Category("Fruits & Vegetables",user_id));
        categories.add(new Category("Dairy",user_id));
        categories.add(new Category("Pantry",user_id));
        categories.add(new Category("Candy",user_id));
        categories.add(new Category("Beverage",user_id));

        db.categoryDAO().insertAll(categories);

        Interest interest_industrial = db.interestDAO().get("Industrial food");
        Interest interest_natural = db.interestDAO().get("Natural food");

        Category category_bakery = db.categoryDAO().getWithStoreAndName(user_id,"Bakery");
        Category category_breakfast = db.categoryDAO().getWithStoreAndName(user_id,"Breakfast");
        Category category_meat = db.categoryDAO().getWithStoreAndName(user_id,"Meat");
        Category category_fruits_vegetables = db.categoryDAO().getWithStoreAndName(user_id,"Fruits & Vegetables");
        Category category_dairy = db.categoryDAO().getWithStoreAndName(user_id,"Dairy");
        Category category_pantry = db.categoryDAO().getWithStoreAndName(user_id,"Pantry");
        Category category_candy = db.categoryDAO().getWithStoreAndName(user_id,"Candy");
        Category category_beverage = db.categoryDAO().getWithStoreAndName(user_id,"Beverage");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_bakery.id_category));
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_breakfast.id_category));
        ifcs.add(new InterestForCategory(interest_natural.id_interest,category_meat.id_category));
        ifcs.add(new InterestForCategory(interest_natural.id_interest,category_fruits_vegetables.id_category));
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_dairy.id_category));
        ifcs.add(new InterestForCategory(interest_natural.id_interest,category_dairy.id_category));
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_pantry.id_category));
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_candy.id_category));
        ifcs.add(new InterestForCategory(interest_industrial.id_interest,category_beverage.id_category));
        ifcs.add(new InterestForCategory(interest_natural.id_interest,category_beverage.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
    }

    private void generateSelfcareTemplate(AppDatabase db,int user_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bath & Body",user_id));
        categories.add(new Category("Hair care",user_id));
        categories.add(new Category("Shave",user_id));
        categories.add(new Category("Sun care & Tanning",user_id));
        categories.add(new Category("Relaxation",user_id));

        db.categoryDAO().insertAll(categories);


        Interest interest_skin_wellness = db.interestDAO().get("Skin wellness");
        Interest interest_hair_wellness = db.interestDAO().get("Hair wellness");

        Category category_bath_body = db.categoryDAO().getWithStoreAndName(user_id,"Bath & Body");
        Category category_hair_care = db.categoryDAO().getWithStoreAndName(user_id,"Hair care");
        Category category_shave = db.categoryDAO().getWithStoreAndName(user_id,"Shave");
        Category category_sun_care_tanning = db.categoryDAO().getWithStoreAndName(user_id,"Sun care & Tanning");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_skin_wellness.id_interest,category_bath_body.id_category));
        ifcs.add(new InterestForCategory(interest_hair_wellness.id_interest,category_hair_care.id_category));
        ifcs.add(new InterestForCategory(interest_hair_wellness.id_interest,category_shave.id_category));
        ifcs.add(new InterestForCategory(interest_skin_wellness.id_interest,category_sun_care_tanning.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
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


        Interest interest_urban = db.interestDAO().get("Urban music");
        Interest interest_classical = db.interestDAO().get("Classical music");

        Category category_guitars = db.categoryDAO().getWithStoreAndName(user_id,"Guitars");
        Category category_basses = db.categoryDAO().getWithStoreAndName(user_id,"Basses");
        Category category_drums = db.categoryDAO().getWithStoreAndName(user_id,"Drums");
        Category category_keys = db.categoryDAO().getWithStoreAndName(user_id,"Keys");
        Category category_strings = db.categoryDAO().getWithStoreAndName(user_id,"Strings");
        Category category_winds = db.categoryDAO().getWithStoreAndName(user_id,"Winds");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_guitars.id_category));
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_basses.id_category));
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_drums.id_category));
        ifcs.add(new InterestForCategory(interest_urban.id_interest,category_drums.id_category));
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_keys.id_category));
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_strings.id_category));
        ifcs.add(new InterestForCategory(interest_classical.id_interest,category_winds.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
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


        Interest interest_capture = db.interestDAO().get("Capture tech");
        Interest interest_entertainment = db.interestDAO().get("Entertainment tech");

        Category category_computers = db.categoryDAO().getWithStoreAndName(user_id,"Computers");
        Category category_video_games = db.categoryDAO().getWithStoreAndName(user_id,"Video Games");
        Category category_cell_phones = db.categoryDAO().getWithStoreAndName(user_id,"Cell-phones");
        Category category_tv = db.categoryDAO().getWithStoreAndName(user_id,"TV");
        Category category_cameras = db.categoryDAO().getWithStoreAndName(user_id,"Cameras");
        Category category_home_studio = db.categoryDAO().getWithStoreAndName(user_id,"Home Studio");
        Category category_wearables = db.categoryDAO().getWithStoreAndName(user_id,"Wearables");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_computers.id_category));
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_video_games.id_category));
        ifcs.add(new InterestForCategory(interest_capture.id_interest,category_cell_phones.id_category));
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_cell_phones.id_category));
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_tv.id_category));
        ifcs.add(new InterestForCategory(interest_capture.id_interest,category_cameras.id_category));
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_home_studio.id_category));
        ifcs.add(new InterestForCategory(interest_entertainment.id_interest,category_wearables.id_category));
        db.interestForCategoryDAO().insertAll(ifcs);
    }
}
