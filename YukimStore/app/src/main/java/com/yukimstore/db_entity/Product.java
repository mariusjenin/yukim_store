package com.yukimstore.db_entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class Product {
    @PrimaryKey
    public int id_product;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "details")
    public String details;

    @ColumnInfo(name = "price")
    public int price;

    private static int idCount = 0;

    public Product(String name, String details, int price) {
        this.id_product = idCount++;
        this.name = name;
        this.details = details;
        this.price = price;
    }
}
