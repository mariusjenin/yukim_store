package com.yukimstore;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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