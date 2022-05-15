package com.yukimstore.dao;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db_entity.User;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM User where User.mail = :mail LIMIT 1")
    User get(String mail);

    @Query("DELETE FROM User")
    void clear();

    @Insert(onConflict = ABORT)
    void insert(User user);

    @Insert(onConflict = REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);
}