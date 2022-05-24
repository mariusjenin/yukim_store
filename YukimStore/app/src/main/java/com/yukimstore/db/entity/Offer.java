package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Offer",
        foreignKeys = @ForeignKey(entity = Product.class,
                parentColumns = "id_product",
                childColumns = "id_product_offer"))
public class Offer implements Serializable {
    @PrimaryKey
    public int id_product_offer;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "date_start")
    public Date date_start;

    @ColumnInfo(name = "date_end")
    public Date date_end;

    public Offer(int id_product_offer, int price, Date date_start, Date date_end) {
        this.id_product_offer = id_product_offer;
        this.price = price;
        this.date_start = date_start;
        this.date_end = date_end;
    }
}