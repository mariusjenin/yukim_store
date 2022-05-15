package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.InterestForCategory;

@Dao
public interface InterestForCategoryDAO {
    @Query("SELECT * FROM InterestForCategory WHERE InterestForCategory.id_ifc = :id LIMIT 1")
    InterestForCategory get(int id);

    @Query("SELECT * FROM InterestForCategory WHERE InterestForCategory.id_category = :id_category")
    InterestForCategory getWithCategory(int id_category);

    @Query("SELECT * FROM InterestForCategory WHERE InterestForCategory.id_interest = :id_interest")
    InterestForCategory getWithInterest(int id_interest);

    @Insert(onConflict = ABORT)
    void insert(InterestForCategory ifc);

    @Query("DELETE FROM UserHasInterest")
    void clear();
}
