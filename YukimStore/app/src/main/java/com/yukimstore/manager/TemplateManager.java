package com.yukimstore.manager;

import com.yukimstore.db.AppDatabase;
import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.InterestForCategory;

import java.util.ArrayList;

public class TemplateManager {

    private static TemplateManager INSTANCE;

    private TemplateManager() {}

    public static TemplateManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TemplateManager();
        }
        return INSTANCE;
    }

    public void generateClothingTemplate(AppDatabase db, int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Tops",store_id));
        categories.add(new Category("Shirts",store_id));
        categories.add(new Category("Sweaters",store_id));
        categories.add(new Category("Dresses",store_id));
        categories.add(new Category("Jeans",store_id));
        categories.add(new Category("Pants",store_id));
        categories.add(new Category("Shorts",store_id));
        categories.add(new Category("Skirts",store_id));
        categories.add(new Category("Coats",store_id));
        categories.add(new Category("Suits",store_id));
        categories.add(new Category("Underwear",store_id));
        categories.add(new Category("Swimwear",store_id));

        db.categoryDAO().insertAll(categories);

        Interest interest_top_garment = db.interestDAO().get("Top garments");
        Interest interest_bot_garment = db.interestDAO().get("Bottom garments");
        Category category_tops = db.categoryDAO().getWithStoreAndName(store_id,"Tops");
        Category category_shirts = db.categoryDAO().getWithStoreAndName(store_id,"Shirts");
        Category category_sweaters = db.categoryDAO().getWithStoreAndName(store_id,"Sweaters");
        Category category_dresses = db.categoryDAO().getWithStoreAndName(store_id,"Dresses");
        Category category_jeans = db.categoryDAO().getWithStoreAndName(store_id,"Jeans");
        Category category_pants = db.categoryDAO().getWithStoreAndName(store_id,"Pants");
        Category category_shorts = db.categoryDAO().getWithStoreAndName(store_id,"Shorts");
        Category category_skirts = db.categoryDAO().getWithStoreAndName(store_id,"Skirts");
        Category category_coats = db.categoryDAO().getWithStoreAndName(store_id,"Coats");
        Category category_suits = db.categoryDAO().getWithStoreAndName(store_id,"Suits");

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

    public void generateSportTemplate(AppDatabase db,int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Athletics",store_id));
        categories.add(new Category("Basketball",store_id));
        categories.add(new Category("Climbing",store_id));
        categories.add(new Category("Football",store_id));
        categories.add(new Category("Golf",store_id));
        categories.add(new Category("Karate",store_id));
        categories.add(new Category("Swimming",store_id));
        categories.add(new Category("Tennis",store_id));
        categories.add(new Category("Paragliding",store_id));

        db.categoryDAO().insertAll(categories);

        Interest interest_classical_sport = db.interestDAO().get("Classical sports");
        Interest interest_extreme_sport = db.interestDAO().get("Extreme sports");

        Category category_athletics = db.categoryDAO().getWithStoreAndName(store_id,"Athletics");
        Category category_basketbal = db.categoryDAO().getWithStoreAndName(store_id,"Basketball");
        Category category_climbing = db.categoryDAO().getWithStoreAndName(store_id,"Climbing");
        Category category_football = db.categoryDAO().getWithStoreAndName(store_id,"Football");
        Category category_golf = db.categoryDAO().getWithStoreAndName(store_id,"Golf");
        Category category_karate = db.categoryDAO().getWithStoreAndName(store_id,"Karate");
        Category category_swimming = db.categoryDAO().getWithStoreAndName(store_id,"Swimming");
        Category category_tennis = db.categoryDAO().getWithStoreAndName(store_id,"Tennis");
        Category category_paragliding = db.categoryDAO().getWithStoreAndName(store_id,"Paragliding");

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

    public void generateFoodTemplate(AppDatabase db,int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bakery",store_id));
        categories.add(new Category("Breakfast",store_id));
        categories.add(new Category("Meat",store_id));
        categories.add(new Category("Fruits & Vegetables",store_id));
        categories.add(new Category("Dairy",store_id));
        categories.add(new Category("Pantry",store_id));
        categories.add(new Category("Candy",store_id));
        categories.add(new Category("Beverage",store_id));

        db.categoryDAO().insertAll(categories);

        Interest interest_industrial = db.interestDAO().get("Industrial food");
        Interest interest_natural = db.interestDAO().get("Natural food");

        Category category_bakery = db.categoryDAO().getWithStoreAndName(store_id,"Bakery");
        Category category_breakfast = db.categoryDAO().getWithStoreAndName(store_id,"Breakfast");
        Category category_meat = db.categoryDAO().getWithStoreAndName(store_id,"Meat");
        Category category_fruits_vegetables = db.categoryDAO().getWithStoreAndName(store_id,"Fruits & Vegetables");
        Category category_dairy = db.categoryDAO().getWithStoreAndName(store_id,"Dairy");
        Category category_pantry = db.categoryDAO().getWithStoreAndName(store_id,"Pantry");
        Category category_candy = db.categoryDAO().getWithStoreAndName(store_id,"Candy");
        Category category_beverage = db.categoryDAO().getWithStoreAndName(store_id,"Beverage");

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

    public void generateSelfcareTemplate(AppDatabase db,int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Bath & Body",store_id));
        categories.add(new Category("Hair care",store_id));
        categories.add(new Category("Shave",store_id));
        categories.add(new Category("Sun care & Tanning",store_id));
        categories.add(new Category("Relaxation",store_id));

        db.categoryDAO().insertAll(categories);


        Interest interest_skin_wellness = db.interestDAO().get("Skin wellness");
        Interest interest_hair_wellness = db.interestDAO().get("Hair wellness");

        Category category_bath_body = db.categoryDAO().getWithStoreAndName(store_id,"Bath & Body");
        Category category_hair_care = db.categoryDAO().getWithStoreAndName(store_id,"Hair care");
        Category category_shave = db.categoryDAO().getWithStoreAndName(store_id,"Shave");
        Category category_sun_care_tanning = db.categoryDAO().getWithStoreAndName(store_id,"Sun care & Tanning");

        ArrayList<InterestForCategory> ifcs = new ArrayList<>();
        ifcs.add(new InterestForCategory(interest_skin_wellness.id_interest,category_bath_body.id_category));
        ifcs.add(new InterestForCategory(interest_hair_wellness.id_interest,category_hair_care.id_category));
        ifcs.add(new InterestForCategory(interest_hair_wellness.id_interest,category_shave.id_category));
        ifcs.add(new InterestForCategory(interest_skin_wellness.id_interest,category_sun_care_tanning.id_category));

        db.interestForCategoryDAO().insertAll(ifcs);
    }

    public void generateMusicTemplate(AppDatabase db,int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Guitars",store_id));
        categories.add(new Category("Basses",store_id));
        categories.add(new Category("Drums",store_id));
        categories.add(new Category("Keys",store_id));
        categories.add(new Category("Strings",store_id));
        categories.add(new Category("Winds",store_id));

        db.categoryDAO().insertAll(categories);


        Interest interest_urban = db.interestDAO().get("Urban music");
        Interest interest_classical = db.interestDAO().get("Classical music");

        Category category_guitars = db.categoryDAO().getWithStoreAndName(store_id,"Guitars");
        Category category_basses = db.categoryDAO().getWithStoreAndName(store_id,"Basses");
        Category category_drums = db.categoryDAO().getWithStoreAndName(store_id,"Drums");
        Category category_keys = db.categoryDAO().getWithStoreAndName(store_id,"Keys");
        Category category_strings = db.categoryDAO().getWithStoreAndName(store_id,"Strings");
        Category category_winds = db.categoryDAO().getWithStoreAndName(store_id,"Winds");

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

    public void generateTechnologyTemplate(AppDatabase db,int store_id) {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Computers",store_id));
        categories.add(new Category("Video Games",store_id));
        categories.add(new Category("Cell-phones",store_id));
        categories.add(new Category("TV",store_id));
        categories.add(new Category("Cameras",store_id));
        categories.add(new Category("Home Studio",store_id));
        categories.add(new Category("Wearables",store_id));

        db.categoryDAO().insertAll(categories);


        Interest interest_capture = db.interestDAO().get("Capture tech");
        Interest interest_entertainment = db.interestDAO().get("Entertainment tech");

        Category category_computers = db.categoryDAO().getWithStoreAndName(store_id,"Computers");
        Category category_video_games = db.categoryDAO().getWithStoreAndName(store_id,"Video Games");
        Category category_cell_phones = db.categoryDAO().getWithStoreAndName(store_id,"Cell-phones");
        Category category_tv = db.categoryDAO().getWithStoreAndName(store_id,"TV");
        Category category_cameras = db.categoryDAO().getWithStoreAndName(store_id,"Cameras");
        Category category_home_studio = db.categoryDAO().getWithStoreAndName(store_id,"Home Studio");
        Category category_wearables = db.categoryDAO().getWithStoreAndName(store_id,"Wearables");

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