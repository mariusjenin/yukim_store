package com.yukimstore;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yukimstore.dao.ProductDAO;
import com.yukimstore.dao.UserDAO;
import com.yukimstore.db_entity.Product;
import com.yukimstore.db_entity.User;

/**
 * Singleton class that manage the database in the whole app
 */
@Database(entities = {User.class, Product.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE = null;
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();

    public static AppDatabase getInstance(Context applicationContext) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(applicationContext,AppDatabase.class,"db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}