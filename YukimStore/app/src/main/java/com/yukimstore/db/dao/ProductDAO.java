package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Product;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM Product WHERE Product.id_product = :id LIMIT 1")
    Product get(int id);

    @Insert(onConflict = ABORT)
    void insert(Product product);

    @Query("DELETE FROM Product")
    void clear();
}
