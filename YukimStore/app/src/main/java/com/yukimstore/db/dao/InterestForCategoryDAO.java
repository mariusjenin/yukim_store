package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Interest;
import com.yukimstore.db.entity.InterestForCategory;

import java.util.List;

@Dao
public interface InterestForCategoryDAO {
    @Query("SELECT * FROM InterestForCategory WHERE InterestForCategory.id_ifc = :id LIMIT 1")
    InterestForCategory get(int id);

    @Query("SELECT Interest.* FROM InterestForCategory inner join Interest on InterestForCategory.id_interest = Interest.id_interest WHERE InterestForCategory.id_category = :id_category")
    List<Interest> getInterestsWithCategory(int id_category);

    @Insert(onConflict = ABORT)
    void insert(InterestForCategory ifc);

    @Query("DELETE FROM InterestForCategory")
    void clear();
}
