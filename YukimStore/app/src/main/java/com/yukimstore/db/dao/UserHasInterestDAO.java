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

    @Query("SELECT * FROM UserHasInterest WHERE UserHasInterest.id_user = :id_user")
    UserHasInterest getWithUser(int id_user);

    @Query("SELECT * FROM UserHasInterest WHERE UserHasInterest.id_interest = :id_interest")
    UserHasInterest getWithInterest(int id_interest);

    @Insert(onConflict = ABORT)
    void insert(UserHasInterest uhi);

    @Query("DELETE FROM UserHasInterest")
    void clear();
}
