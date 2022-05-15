package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Interest;

@Dao
public interface InterestDAO {
    @Query("SELECT * FROM Interest WHERE Interest.id_interest = :id LIMIT 1")
    Interest get(int id);

    @Insert(onConflict = ABORT)
    void insert(Interest interest);

    @Query("DELETE FROM interest")
    void clear();
}
