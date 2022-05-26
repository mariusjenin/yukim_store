package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Category;
import com.yukimstore.db.entity.Interest;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface InterestDAO {
    @Query("SELECT * FROM Interest WHERE Interest.id_interest = :id LIMIT 1")
    Interest get(int id);

    @Query("SELECT * FROM Interest WHERE Interest.name = :name LIMIT 1")
    Interest get(String name);

    @Query("SELECT * FROM Interest")
    List<Interest> getAll();

    @Insert(onConflict = ABORT)
    void insert(Interest interest);

    @Insert(onConflict = ABORT)
    void insertAll(ArrayList<Interest> interests);

    @Query("DELETE FROM interest")
    void clear();
}
