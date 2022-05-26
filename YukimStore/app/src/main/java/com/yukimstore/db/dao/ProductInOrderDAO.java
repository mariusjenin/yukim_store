package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.ProductInBasket;
import com.yukimstore.db.entity.ProductInOrder;

import java.util.List;

@Dao
public interface ProductInOrderDAO {
    @Query("SELECT * FROM ProductInOrder WHERE ProductInOrder.id_pio = :id LIMIT 1")
    ProductInOrder get(int id);

    @Query("SELECT Product.* FROM ProductInOrder inner join Product on Product.id_product = ProductInOrder.id_product WHERE ProductInOrder.id_order = :id_order")
    List<Product> getProductsWithOrder(int id_order);

    @Query("SELECT * FROM ProductInOrder WHERE ProductInOrder.id_order = :id_order and ProductInOrder.id_product = :id_product LIMIT 1")
    ProductInOrder getWithOrderAndProduct(int id_order,int id_product);

    @Query("SELECT sum(ProductInOrder.total_price) FROM ProductInOrder WHERE ProductInOrder.id_order = :id_order")
    float getSumOrder(int id_order);

    @Insert(onConflict = ABORT)
    long insert(ProductInOrder pio);

    @Insert(onConflict = ABORT)
    void insertAll(List<ProductInOrder> pios);

    @Query("DELETE FROM ProductInOrder")
    void clear();
}
