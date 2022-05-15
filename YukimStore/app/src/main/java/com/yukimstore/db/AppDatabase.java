package com.yukimstore.db;

import android.content.Context;

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
},version = 2, exportSchema = false)
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
        userDAO().clear();
        productDAO().clear();
        storeDAO().clear();
        categoryDAO().clear();
        offerDAO().clear();
        interestDAO().clear();
        userHasInterestDAO().clear();
        interestForCategoryDAO().clear();
        productInBasketDAO().clear();
        orderDAO().clear();
        productInOrderDAO().clear();
    }
}