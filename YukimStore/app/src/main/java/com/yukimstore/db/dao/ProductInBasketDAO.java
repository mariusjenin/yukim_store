package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.ProductInBasket;

import java.util.List;

@Dao
public interface ProductInBasketDAO {
    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_pib = :id LIMIT 1")
    ProductInBasket get(int id);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_user = :id_user")
    List<ProductInBasket> getWithUser(int id_user);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_product = :id_product")
    List<ProductInBasket> getWithProduct(int id_product);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_product = :id_product and ProductInBasket.id_user = :id_user")
    ProductInBasket getWithProductAndUser(int id_product,int id_user);

    @Query("SELECT sum(total_price_product) FROM (SELECT ProductInBasket.quantity * Product.price as total_price_product FROM ProductInBasket inner join Product on ProductInBasket.id_product = Product.id_product " +
            "WHERE ProductInBasket.id_user = :id_user)")
    int getSumBasket(int id_user);

    @Query("UPDATE ProductInBasket SET quantity = :quantity WHERE ProductInBasket.id_pib = :id_pib")
    void changeQuantity(int id_pib, int quantity);

    @Insert(onConflict = ABORT)
    void insert(ProductInBasket pib);

    @Query("DELETE FROM ProductInBasket where ProductInBasket.id_user = :id_user")
    void clearBasket(int id_user);

    @Query("DELETE FROM ProductInBasket where ProductInBasket.id_pib = :id_pib")
    void clear(int id_pib);

    @Query("DELETE FROM ProductInBasket")
    void clear();
}
