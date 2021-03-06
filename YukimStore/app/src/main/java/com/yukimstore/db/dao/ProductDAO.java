package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Order;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM Product WHERE Product.id_product = :id LIMIT 1")
    Product get(int id);

    @Query("SELECT * FROM Product where Product.name like '%' || :str || '%' or Product.details like '%' || :str || '%'")
    List<Product> getProductsLike(String str);

    @Query("SELECT distinct Product.* FROM Product " +
            "inner join Category on Product.id_category = Category.id_category " +
            "inner join InterestForCategory on Category.id_category = InterestForCategory.id_category " +
            "inner join UserHasInterest on InterestForCategory.id_interest = UserHasInterest.id_interest " +
            "where UserHasInterest.id_user = :id_user")
    List<Product> getProductsInterestingForUser(int id_user);

    @Insert(onConflict = ABORT)
    void insert(Product product);

    @Insert(onConflict = ABORT)
    void insertAll(List<Product> products);

    @Query("DELETE FROM Product")
    void clear();
}
