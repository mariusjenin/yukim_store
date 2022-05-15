package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Store;

@Dao
public interface StoreDAO {
    @Query("SELECT * FROM Store where Store.id_user_store = :id LIMIT 1")
    Store get(String id);

    @Query("DELETE FROM Store")
    void clear();

    @Insert(onConflict = ABORT)
    void insert(Store store);

    @Insert(onConflict = REPLACE)
    void insertAll(Store... stores);

    @Delete
    void delete(Store store);
}