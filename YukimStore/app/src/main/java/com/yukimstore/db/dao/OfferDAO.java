package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Offer;

@Dao
public interface OfferDAO {
    @Query("SELECT * FROM Offer WHERE Offer.id_product_offer = :id LIMIT 1")
    Offer get(int id);

    @Insert(onConflict = ABORT)
    void insert(Offer offer);

    @Query("DELETE FROM Offer")
    void clear();
}
