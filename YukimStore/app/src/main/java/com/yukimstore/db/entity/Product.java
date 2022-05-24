package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Product",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id_category",
                childColumns = "id_category"))
public class Product implements Serializable {
    @PrimaryKey
    public int id_product;

    @ColumnInfo(name = "id_category", index = true)
    public int id_category;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "details")
    public String details;

    @ColumnInfo(name = "price")
    public float price;

    @ColumnInfo(name = "img")
    public String img;

    private static int idCount = 0;

    public Product(String name, String details, float price, int id_category, String img) {
        this.id_product = idCount++;
        this.id_category = id_category;
        this.name = name;
        this.details = details;
        this.price = price;
        this.img = img;
    }
}
