package com.yukimstore.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yukimstore.db.dao.ProductDAO;
import com.yukimstore.db.dao.UserDAO;
import com.yukimstore.db.dao.StoreDAO;
import com.yukimstore.db.dao.CategoryDAO;
import com.yukimstore.db.dao.OfferDAO;
import com.yukimstore.db.dao.InterestDAO;
import com.yukimstore.db.dao.UserHasInterestDAO;
import com.yukimstore.db.dao.InterestForCategoryDAO;
import com.yukimstore.db.dao.ProductInBasketDAO;
import com.yukimstore.db.dao.OrderDAO;
import com.yukimstore.db.dao.ProductInOrderDAO;

import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;
import com.yukimstore.db.entity.User;
import com.yukimstore.db.entity.Offer;
import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.UserHasInterest;
import com.yukimstore.db.entity.InterestForCategory;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.ProductInOrder;
import com.yukimstore.manager.TemplateManager;
import com.yukimstore.utils.HashUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Singleton class that manage the database in the whole app
 */
@Database(entities = {
        User.class,
        Product.class,
        Store.class,
        Category.class,
        Offer.class,
        Interest.class,
        UserHasInterest.class,
        InterestForCategory.class,
        ProductInBasket.class,
        Order.class,
        ProductInOrder.class
},version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE = null;
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
    public abstract StoreDAO storeDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract OfferDAO offerDAO();
    public abstract InterestDAO interestDAO();
    public abstract UserHasInterestDAO userHasInterestDAO();
    public abstract InterestForCategoryDAO interestForCategoryDAO();
    public abstract ProductInBasketDAO productInBasketDAO();
    public abstract OrderDAO orderDAO();
    public abstract ProductInOrderDAO productInOrderDAO();

    public static AppDatabase getInstance(Context applicationContext) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(applicationContext,AppDatabase.class,"db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public void clear(){
        offerDAO().clear();
        userHasInterestDAO().clear();
        interestForCategoryDAO().clear();
        interestDAO().clear();
        productInOrderDAO().clear();
        orderDAO().clear();
        productInBasketDAO().clear();
        productDAO().clear();
        categoryDAO().clear();
        storeDAO().clear();
        userDAO().clear();
    }

    public void fillUsers(){
        UserDAO userDAO = userDAO();
        ArrayList<User> users = new ArrayList<>();

        //Merchant
        users.add(new User("store@zara.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));
        users.add(new User("store@decathlon.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));
        users.add(new User("store@carrefour.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));
        users.add(new User("store@naturea.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));
        users.add(new User("store@woodbrass.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));
        users.add(new User("store@fnac.fr", HashUtil.getSHA256SecurePassword("password",""),true,"",""));

        //Client
        users.add(new User("william.puech@lirmm.fr", HashUtil.getSHA256SecurePassword("password",""),false,"John","Doe"));

        userDAO.insertAll(users);
    }

    public void fillStores(){
        StoreDAO storeDAO = storeDAO();
        ArrayList<Store> stores = new ArrayList<>();

        stores.add(new Store(userDAO().get("store@zara.fr").id_user,"Zara", null));
        stores.add(new Store(userDAO().get("store@decathlon.fr").id_user,"Decathlon", null));
        stores.add(new Store(userDAO().get("store@woodbrass.fr").id_user,"Woodbrass", null));
        stores.add(new Store(userDAO().get("store@fnac.fr").id_user,"Fnac", null));
        stores.add(new Store(userDAO().get("store@carrefour.fr").id_user,"Carrefour", null));
        stores.add(new Store(userDAO().get("store@naturea.fr").id_user,"Naturea", null));

        storeDAO.insertAll(stores);
    }

    public void fillInterests(){
        InterestDAO interestDAO = interestDAO();
        ArrayList<Interest> interests = new ArrayList<>();

        interests.add(new Interest("Extreme sports"));
        interests.add(new Interest("Classical sports"));
        interests.add(new Interest("Industrial food"));
        interests.add(new Interest("Natural food"));
        interests.add(new Interest("Skin wellness"));
        interests.add(new Interest("Hair wellness"));
        interests.add(new Interest("Urban music"));
        interests.add(new Interest("Classical music"));
        interests.add(new Interest("Capture tech"));
        interests.add(new Interest("Entertainment tech"));
        interests.add(new Interest("Top garments"));
        interests.add(new Interest("Bottom garments"));

        interestDAO.insertAll(interests);
    }

    public void fillUsersHaveInterests(){
        UserHasInterestDAO userHasInterestDAO = userHasInterestDAO();
        ArrayList<UserHasInterest> uhis = new ArrayList<>();

        uhis.add(new UserHasInterest(interestDAO().get("Extreme sports").id_interest,userDAO().get("william.puech@lirmm.fr").id_user));
        uhis.add(new UserHasInterest(interestDAO().get("Classical sports").id_interest,userDAO().get("william.puech@lirmm.fr").id_user));

        userHasInterestDAO.insertAll(uhis);
    }

    public void fillCategories(){
        TemplateManager tm = TemplateManager.getInstance();

        tm.generateClothingTemplate(this,userDAO().get("store@zara.fr").id_user);
        tm.generateSportTemplate(this,userDAO().get("store@decathlon.fr").id_user);
        tm.generateMusicTemplate(this,userDAO().get("store@woodbrass.fr").id_user);
        tm.generateTechnologyTemplate(this,userDAO().get("store@fnac.fr").id_user);
        tm.generateFoodTemplate(this,userDAO().get("store@carrefour.fr").id_user);
        tm.generateSelfcareTemplate(this,userDAO().get("store@naturea.fr").id_user);
    }

    public void fillProducts(){
        ProductDAO productDAO = productDAO();
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Plain Tshirt","White, 100% Cotton, L", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Tops").id_category,null));
        products.add(new Product("Oversize Tshirt","Black, 100% Cotton, M", 24.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Tops").id_category,null));
        products.add(new Product("Long Sleeve Button-down Shirt","Plaid red, 100% Cotton Flannel, S", 39.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Shirts").id_category,null));
        products.add(new Product("Sweatshirt","Grey, 90% Cotton 10% Polyester, M", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Sweaters").id_category,null));
        products.add(new Product("Cocktail Dress","Black, 100% Polyester, 38", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Dresses").id_category,null));
        products.add(new Product("Vintage Jeans","Bleached, 98% Cotton 2% Elastane, 40", 39.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Jeans").id_category,null));
        products.add(new Product("Flare Trousers","Purple, 90% Cotton 10% Polyester, 40", 24.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Pants").id_category,null));
        products.add(new Product("Cargo Short","Kaki, 100% Cotton, M", 19.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Shorts").id_category,null));
        products.add(new Product("Mini Skirt","Pale Pink, 100% Polyester, 36", 19.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Skirts").id_category,null));
        products.add(new Product("Trench Coat","Beige, 100% Wool, 40", 99.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Coats").id_category,null));
        products.add(new Product("Three Pieces Suit","Navy, 98% Wool 2% Polyester, 40", 149.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Suits").id_category,null));
        products.add(new Product("2-pack Trunks","Black, 100% Cotton, M", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Underwear").id_category,null));
        products.add(new Product("Swimsuit","Blue/Orange Print, Unique", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@zara.fr").id_user,"Swimwear").id_category,null));

        products.add(new Product("Running Shoes","Grey/White, 43", 19.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Athletics").id_category,null));
        products.add(new Product("Jersey","Red/Green, L", 19.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Basketball").id_category,null));
        products.add(new Product("Ropes","5m", 4.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Climbing").id_category,null));
        products.add(new Product("Ball","World Cup 2022", 9.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Football").id_category,null));
        products.add(new Product("Club set","3 Clubs (1 Driver, 1 Sand Wedge, 1 Putter), 3 balls", 199.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Golf").id_category,null));
        products.add(new Product("Kimono","White", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Karate").id_category,null));
        products.add(new Product("Swim Goggles","Blue", 9.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Swimming").id_category,null));
        products.add(new Product("Tennis Racket","Standard", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Tennis").id_category,null));
        products.add(new Product("Wing","Standard", 299.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@decathlon.fr").id_user,"Paragliding").id_category,null));

        products.add(new Product("Plain Bread","300g", 0.87f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Bakery").id_category,null));
        products.add(new Product("Croissants","6 pieces", 2.49f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Breakfast").id_category,null));
        products.add(new Product("Rib-eye Steak","900g", 24.90f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Meat").id_category,null));
        products.add(new Product("Apples","Granny Smith, 1kg", 2.10f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Fruits & Vegetables").id_category,null));
        products.add(new Product("Fresh Milk","1L", 0.80f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Dairy").id_category,null));
        products.add(new Product("Canned Tuna","200g", 1.75f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Pantry").id_category,null));
        products.add(new Product("Haribo Dragibus","150g", 1.37f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Candy").id_category,null));
        products.add(new Product("Lipton Ice Tea","1.5L", 1.89f, categoryDAO().getWithStoreAndName(userDAO().get("store@carrefour.fr").id_user,"Beverage").id_category,null));

        products.add(new Product("Natural Soap","Lemon & Parsimon, 100g", 3.40f, categoryDAO().getWithStoreAndName(userDAO().get("store@naturea.fr").id_user,"Bath & Body").id_category,null));
        products.add(new Product("Blue Hair Dye","Tumblr certified", 5.79f, categoryDAO().getWithStoreAndName(userDAO().get("store@naturea.fr").id_user,"Hair care").id_category,null));
        products.add(new Product("Electric Trimmer","7 different caps, 25W", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("store@naturea.fr").id_user,"Shave").id_category,null));
        products.add(new Product("Solar Cream","Sensitive skin, 125mL", 8.42f, categoryDAO().getWithStoreAndName(userDAO().get("store@naturea.fr").id_user,"Sun care & Tanning").id_category,null));
        products.add(new Product("Indian Incenses","10 fragrances", 17.59f, categoryDAO().getWithStoreAndName(userDAO().get("store@naturea.fr").id_user,"Relaxation").id_category,null));

        products.add(new Product("Fender Player Stratocaster PF Fiesta Red","Electric Guitar, Rightist, Maple, 3 mics (Alnico V)", 769.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Guitars").id_category,null));
        products.add(new Product("Yamaha TRBX504 Trans Black","Bass, Leftist, Mahogany, 2 mics (Alnico V)", 569.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Basses").id_category,null));
        products.add(new Product("Pearl Drums Export Standard 22","22\"x18\" Bass Drum, 12\"x8\" Tom, 13\"x9\" Tom, 16\"x16\" Floor Tom, 14\"x5.5\" Snare Drum", 825.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Drums").id_category,null));
        products.add(new Product("Yamaha PSR-E473","Keyboard, 61 velocity-sensitive keys, 820 sounds, 290 models, 64 voice polyphony", 377.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Keys").id_category,null));
        products.add(new Product("Eagltone Rimini 4/4","Violin, Spruce/Maple/Ebony", 149.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Strings").id_category,null));
        products.add(new Product("Jupiter JAS500Q","Saxophone, Body yellow brass, High F# key, Gold lacquer finish, Key Eb", 860.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@woodbrass.fr").id_user,"Winds").id_category,null));

        products.add(new Product("ASUS Zephyrus M16","Laptop, i7 11800H, RTX 3060, 16GB", 1999.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Computers").id_category,null));
        products.add(new Product("The Legend of Zelda : BOTW 2","Nintendo Switch, Action/Adventure, PEGI 12", 59.90f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Video Games").id_category,null));
        products.add(new Product("Apple iPhone 13","Black, 256GB, Apple A15, 5G", 979.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Cell-phones").id_category,null));
        products.add(new Product("LG OLED55C1","4K UHD, OLED, SmartTV, 4 HDMI / 3 USB", 1299.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"TV").id_category,null));
        products.add(new Product("Panasonic Lumix GH5","20,3Mpx, 3.2\" screen, OLED sensor, 2-axis stabilization, 4K, Wi-Fi", 1449.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Cameras").id_category,null));
        products.add(new Product("Enclave EA1000THXUS CineHome Pro 5.1","Custom Drivers, 11 Class-D Digital Amplifiers, Full-Range Rears, 10 Subwoofer, Speaker Level Setup, Whole Room Stereo, Dolby and DTS Audio", 1840.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Home Studio").id_category,null));
        products.add(new Product("Apple Watch Series 7","GPS, 41mm Starlight Aluminum Case with Starlight Sport Band, Regular", 389.0f, categoryDAO().getWithStoreAndName(userDAO().get("store@fnac.fr").id_user,"Wearables").id_category,null));

        productDAO.insertAll(products);
    }

    public void init(){
        clear();
        fillUsers();
        fillStores();
        fillInterests();
        fillUsersHaveInterests();
        fillCategories();
        fillProducts();
    }
}
