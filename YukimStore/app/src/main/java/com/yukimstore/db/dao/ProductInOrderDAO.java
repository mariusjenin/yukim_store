package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.ProductInOrder;

@Dao
public interface ProductInOrderDAO {
    @Query("SELECT * FROM ProductInOrder WHERE ProductInOrder.id_pio = :id LIMIT 1")
    ProductInOrder get(int id);

    @Query("SELECT * FROM ProductInOrder WHERE ProductInOrder.id_product = :id_product")
    ProductInOrder getWithProduct(int id_product);

    @Query("SELECT * FROM ProductInOrder WHERE ProductInOrder.id_order = :id_order")
    ProductInOrder getWithOrder(int id_order);

    @Insert(onConflict = ABORT)
    void insert(ProductInOrder pio);

    @Query("DELETE FROM ProductInOrder")
    void clear();
}
