package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.ProductInBasket;

@Dao
public interface ProductInBasketDAO {
    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_pib = :id LIMIT 1")
    ProductInBasket get(int id);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_user = :id_user")
    ProductInBasket getWithUser(int id_user);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_product = :id_product")
    ProductInBasket getWithProduct(int id_product);

    @Insert(onConflict = ABORT)
    void insert(ProductInBasket pib);

    @Query("DELETE FROM ProductInBasket")
    void clear();
}
