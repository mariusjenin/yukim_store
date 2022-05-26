package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.UserHasInterest;

@Dao
public interface UserHasInterestDAO {
    @Query("SELECT * FROM UserHasInterest WHERE UserHasInterest.id_uhi = :id LIMIT 1")
    UserHasInterest get(int id);

    @Query("SELECT * FROM UserHasInterest WHERE UserHasInterest.id_interest = :id_interest and UserHasInterest.id_user = :id_user")
    UserHasInterest getWithUserAndInterest(int id_user,int id_interest);

    @Insert(onConflict = ABORT)
    void insert(UserHasInterest uhi);

    @Query("DELETE FROM UserHasInterest")
    void clear();

    @Query("DELETE FROM UserHasInterest WHERE UserHasInterest.id_user = :id_user")
    void clearForUser(int id_user);
}
