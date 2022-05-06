package com.yukimstore;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM Product WHERE Product.id_product = :id")
    Product get(int id);

    @Insert(onConflict = ABORT)
    void insert(Product product);

    @Query("DELETE FROM Product")
    void clear();
}
