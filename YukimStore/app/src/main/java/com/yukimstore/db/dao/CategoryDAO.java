package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Category;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category WHERE Category.id_category = :id LIMIT 1")
    Category get(int id);

    @Insert(onConflict = ABORT)
    void insert(Category category);

    @Query("DELETE FROM Category")
    void clear();
}
