package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Product;
import com.yukimstore.db.entity.Store;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category WHERE Category.id_category = :id LIMIT 1")
    Category get(int id);


    @Query("SELECT * FROM Category WHERE Category.id_store = :id_store and Category.name = :name LIMIT 1")
    Category getWithStoreAndName(int id_store, String name);

    @Query("SELECT * FROM Category WHERE Category.id_store = :id_store")
    List<Category> getCategoriesWithStore(int id_store);

    @Query("SELECT distinct Product.* FROM Category INNER JOIN Product on Product.id_category = Category.id_category where Category.id_store = :id_store")
    List<Product> getProductsOfCategoriesofStore(int id_store);

    @Query("SELECT distinct Product.* FROM Category INNER JOIN Product on Product.id_category = Category.id_category where Category.id_category = :id_category")
    List<Product> getProductsOfCategory(int id_category);

    @Insert(onConflict = ABORT)
    void insert(Category category);

    @Query("DELETE FROM Category")
    void clear();
}
