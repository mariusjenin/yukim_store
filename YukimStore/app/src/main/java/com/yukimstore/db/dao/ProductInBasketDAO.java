package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.Store;

import java.util.List;

@Dao
public interface ProductInBasketDAO {
    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_pib = :id LIMIT 1")
    ProductInBasket get(int id);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_user = :id_user")
    List<ProductInBasket> getWithUser(int id_user);

    @Query("SELECT Distinct Store.* FROM ProductInBasket " +
            "inner join Product on ProductInBasket.id_product = Product.id_product " +
            "inner join Category on Product.id_category = Category.id_category " +
            "inner join Store on Category.id_store = Store.id_user_store " +
            "WHERE ProductInBasket.id_user = :id_user")
    List<Store> getStoreWithProductsInBasket(int id_user);

    @Query("SELECT * FROM ProductInBasket WHERE ProductInBasket.id_product = :id_product and ProductInBasket.id_user = :id_user")
    ProductInBasket getWithProductAndUser(int id_product,int id_user);

    @Query("SELECT ProductInBasket.* FROM ProductInBasket " +
            "inner join Product on ProductInBasket.id_product = Product.id_product " +
            "inner join Category on Product.id_category = Category.id_category " +
            "WHERE Category.id_store = :id_store and ProductInBasket.id_user = :id_user")
    List<ProductInBasket> getWithStoreAndUser(int id_store,int id_user);

    @Query("SELECT sum(total_price_product) FROM (SELECT ProductInBasket.quantity * Product.price as total_price_product FROM ProductInBasket inner join Product on ProductInBasket.id_product = Product.id_product " +
            "WHERE ProductInBasket.id_user = :id_user)")
    float getSumBasket(int id_user);

    @Query("UPDATE ProductInBasket SET quantity = :quantity WHERE ProductInBasket.id_pib = :id_pib")
    void changeQuantity(int id_pib, int quantity);

    @Insert(onConflict = ABORT)
    void insert(ProductInBasket pib);

    @Insert(onConflict = ABORT)
    void insertAll(List<ProductInBasket> pibs);

    @Query("DELETE FROM ProductInBasket where ProductInBasket.id_user = :id_user")
    void clearBasket(int id_user);

    @Query("DELETE FROM ProductInBasket where ProductInBasket.id_pib = :id_pib")
    void clear(int id_pib);

    @Query("DELETE FROM ProductInBasket")
    void clear();
}
