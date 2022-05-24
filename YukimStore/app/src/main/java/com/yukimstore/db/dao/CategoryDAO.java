package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category WHERE Category.id_category = :id LIMIT 1")
    Category get(int id);


    @Query("SELECT * FROM Category WHERE Category.id_store = :id_store and Category.name = :name LIMIT 1")
    Category getWithStoreAndName(int id_store, String name);

    @Query("SELECT * FROM Category WHERE Category.id_store = :id_store")
    List<Category> getCategoriesWithStore(int id_store);

    @Insert(onConflict = ABORT)
    void insert(Category category);

    @Query("DELETE FROM Category")
    void clear();
}
