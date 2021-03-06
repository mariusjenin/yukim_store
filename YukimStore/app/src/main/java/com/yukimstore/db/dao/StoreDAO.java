package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Store;

import java.util.List;

@Dao
public interface StoreDAO {
    @Query("SELECT * FROM Store where Store.id_user_store = :id LIMIT 1")
    Store get(int id);

    @Query("SELECT * FROM Store where Store.name like '%' || :name_store || '%'")
    List<Store> getStoresLike(String name_store);

    @Query("DELETE FROM Store")
    void clear();

    @Insert(onConflict = ABORT)
    void insert(Store store);

    @Insert(onConflict = ABORT)
    void insertAll(List<Store> stores);

    @Delete
    void delete(Store store);
}