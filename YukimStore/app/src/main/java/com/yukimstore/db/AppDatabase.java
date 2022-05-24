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
import com.yukimstore.utils.HashUtil;

import java.util.ArrayList;
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
        productInBasketDAO().clear();
        productDAO().clear();
        categoryDAO().clear();
        storeDAO().clear();
        userDAO().clear();

//        offerDAO().clear();
//        interestDAO().clear();
//        userHasInterestDAO().clear();
//        interestForCategoryDAO().clear();
//        orderDAO().clear();
//        productInOrderDAO().clear();
    }

    public void fillUsers(){
        UserDAO userDAO = userDAO();
        ArrayList<User> users = new ArrayList<>();
        //Merchant
        users.add(new User("m0@m0.m0", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m1@m1.m1", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m2@m2.m2", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m3@m3.m3", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m4@m4.m4", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m5@m5.m5", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m6@m6.m6", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m7@m7.m7", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m8@m8.m8", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        users.add(new User("m9@m9.m9", HashUtil.getSHA256SecurePassword("aaaaaa",""),true,"",""));
        //Client
        users.add(new User("c0@c0.c0", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c0","c0"));
        users.add(new User("c1@c1.c1", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c1","c1"));
        users.add(new User("c2@c2.c2", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c2","c2"));
        users.add(new User("c3@c3.c3", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c3","c3"));
        users.add(new User("c4@c4.c4", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c4","c4"));
        users.add(new User("c5@c5.c5", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c5","c5"));
        users.add(new User("c6@c6.c6", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c6","c6"));
        users.add(new User("c7@c7.c7", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c7","c7"));
        users.add(new User("c8@c8.c8", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c8","c8"));
        users.add(new User("c9@c9.c9", HashUtil.getSHA256SecurePassword("aaaaaa",""),false,"c9","c9"));
        //Debug
        users.add(new User("c@c.c", HashUtil.getSHA256SecurePassword("cccccc",""),false,"c","c"));
        users.add(new User("m@m.m", HashUtil.getSHA256SecurePassword("mmmmmm",""),true,"m","m"));
        int size_users = users.size();
        Log.i("","USER INITILIZATION");
        for(int i = 0 ; i < size_users; i++){
            userDAO.insert(users.get(i));
            Log.i("",users.get(i).mail);
        }
    }

    public void fillStores(){
        StoreDAO storeDAO = storeDAO();
        ArrayList<Store> stores = new ArrayList<>();
        stores.add(new Store(userDAO().get("m0@m0.m0").id_user,"Store abc", null));
        stores.add(new Store(userDAO().get("m1@m1.m1").id_user,"Store bcd", null));
        stores.add(new Store(userDAO().get("m2@m2.m2").id_user,"Store cde", null));
        stores.add(new Store(userDAO().get("m3@m3.m3").id_user,"Store def", null));
        stores.add(new Store(userDAO().get("m4@m4.m4").id_user,"Store efg", null));
        stores.add(new Store(userDAO().get("m5@m5.m5").id_user,"Store fgh", null));
        stores.add(new Store(userDAO().get("m6@m6.m6").id_user,"Store ghi", null));
        stores.add(new Store(userDAO().get("m7@m7.m7").id_user,"Store hij", null));
        stores.add(new Store(userDAO().get("m8@m8.m8").id_user,"Store ijk", null));
        stores.add(new Store(userDAO().get("m9@m9.m9").id_user,"Store jkl", null));
        int size_stores = stores.size();
        Log.i("","STORE INITILIZATION");
        for(int i = 0 ; i < size_stores; i++){
            storeDAO.insert(stores.get(i));
            Log.i("",stores.get(i).name);
        }
    }

    public void fillCategories(){
        CategoryDAO categoryDAO = categoryDAO();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Cat 1", userDAO().get("m0@m0.m0").id_user));
        categories.add(new Category("Cat 2", userDAO().get("m0@m0.m0").id_user));
        categories.add(new Category("Cat 3", userDAO().get("m0@m0.m0").id_user));
        categories.add(new Category("Cat 4", userDAO().get("m0@m0.m0").id_user));
        categories.add(new Category("Cat 5", userDAO().get("m0@m0.m0").id_user));
        categories.add(new Category("Cat 6", userDAO().get("m1@m1.m1").id_user));
        categories.add(new Category("Cat 7", userDAO().get("m1@m1.m1").id_user));
        categories.add(new Category("Cat 8", userDAO().get("m1@m1.m1").id_user));
        categories.add(new Category("Cat 9", userDAO().get("m1@m1.m1").id_user));
        categories.add(new Category("Cat 10", userDAO().get("m1@m1.m1").id_user));
        int size_categories = categories.size();
        Log.i("","CATEGORIES INITILIZATION");
        for(int i = 0 ; i < size_categories; i++){
            categoryDAO.insert(categories.get(i));
            Log.i("",categories.get(i).name);
        }
    }

    public void fillProducts(){
        ProductDAO productDAO = productDAO();
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Prod 1","details prod 1", 0.99f, categoryDAO().getWithStoreAndName(userDAO().get("m0@m0.m0").id_user,"Cat 1").id_category,null));
        products.add(new Product("Prod 2","details prod 2", 9.99f, categoryDAO().getWithStoreAndName(userDAO().get("m0@m0.m0").id_user,"Cat 1").id_category,null));
        products.add(new Product("Prod 3","details prod 3", 19.99f, categoryDAO().getWithStoreAndName(userDAO().get("m0@m0.m0").id_user,"Cat 1").id_category,null));
        products.add(new Product("Prod 4","details prod 4", 29.99f, categoryDAO().getWithStoreAndName(userDAO().get("m0@m0.m0").id_user,"Cat 1").id_category,null));
        products.add(new Product("Prod 5","details prod 5", 39.99f, categoryDAO().getWithStoreAndName(userDAO().get("m0@m0.m0").id_user,"Cat 1").id_category,null));
        products.add(new Product("Prod 6","details prod 6", 49.99f, categoryDAO().getWithStoreAndName(userDAO().get("m1@m1.m1").id_user,"Cat 6").id_category,null));
        products.add(new Product("Prod 7","details prod 7", 59.99f, categoryDAO().getWithStoreAndName(userDAO().get("m1@m1.m1").id_user,"Cat 6").id_category,null));
        products.add(new Product("Prod 8","details prod 8", 69.99f, categoryDAO().getWithStoreAndName(userDAO().get("m1@m1.m1").id_user,"Cat 6").id_category,null));
        products.add(new Product("Prod 9","details prod 9", 79.99f, categoryDAO().getWithStoreAndName(userDAO().get("m1@m1.m1").id_user,"Cat 6").id_category,null));
        products.add(new Product("Prod 10","details prod 10", 89.99f, categoryDAO().getWithStoreAndName(userDAO().get("m1@m1.m1").id_user,"Cat 6").id_category,null));
        int size_products = products.size();
        Log.i("","PRODUCTS INITILIZATION");
        for(int i = 0 ; i < size_products; i++){
            productDAO.insert(products.get(i));
            Log.i("",products.get(i).name);
        }
    }

    public void fillProductsInBaskets(){
        ProductInBasketDAO productInBasketDAO = productInBasketDAO();
        ArrayList<ProductInBasket> pibs = new ArrayList<>();
        pibs.add(new ProductInBasket(productDAO().getProductsLike("Prod 1").get(0).id_product,userDAO().get("c@c.c").id_user,1));
        pibs.add(new ProductInBasket(productDAO().getProductsLike("Prod 2").get(0).id_product,userDAO().get("c@c.c").id_user,4));
        pibs.add(new ProductInBasket(productDAO().getProductsLike("Prod 4").get(0).id_product,userDAO().get("c@c.c").id_user,2));
        pibs.add(new ProductInBasket(productDAO().getProductsLike("Prod 5").get(0).id_product,userDAO().get("c@c.c").id_user,1));
        pibs.add(new ProductInBasket(productDAO().getProductsLike("Prod 6").get(0).id_product,userDAO().get("c@c.c").id_user,3));
        int size_pibs = pibs.size();
        Log.i("","PRODUCTS IN BASKET INITILIZATION");
        for(int i = 0 ; i < size_pibs; i++){
            productInBasketDAO.insert(pibs.get(i));
            Log.i("",pibs.get(i).id_product+ " " + pibs.get(i).id_user+ " " + pibs.get(i).quantity);
        }
    }

    public void init(){
        clear();
        fillUsers();
        fillStores();
        fillCategories();
        fillProducts();
        fillProductsInBaskets();
    }
}