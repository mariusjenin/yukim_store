package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Offer;

import java.util.Date;

@Dao
public interface OfferDAO {
    @Query("SELECT * FROM Offer WHERE Offer.id_offer = :id LIMIT 1")
    Offer get(int id);

    @Query("SELECT * FROM Offer WHERE Offer.id_product == :id_product and Offer.date_end > :date and Offer.date_start <= :date order by Offer.date_start DESC, Offer.date_end ASC LIMIT 1")
    Offer get(int id_product, Date date);

    @Insert(onConflict = ABORT)
    void insert(Offer offer);

    @Query("DELETE FROM Offer")
    void clear();
}