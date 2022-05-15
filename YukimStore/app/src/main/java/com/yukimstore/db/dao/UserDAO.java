package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.User;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM User where User.id_user = :id LIMIT 1")
    User get(int id);

    @Query("SELECT * FROM User where User.mail = :mail LIMIT 1")
    User get(String mail);

    @Query("SELECT * FROM User where User.token_connect = :token LIMIT 1")
    User getWithToken(String token);

    @Query("DELETE FROM User")
    void clear();

    @Insert(onConflict = ABORT)
    void insert(User user);

    @Insert(onConflict = REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);
}