package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category",
        foreignKeys = @ForeignKey(entity = Store.class,
                parentColumns = "id_user_store",
                childColumns = "id_store"))
public class Category {
    @PrimaryKey
    public int id_category;

    @ColumnInfo(name = "id_store", index = true)
    public int id_store;

    @ColumnInfo(name = "name")
    public String name;

    private static int idCount = 0;

    public Category(String name, int id_store) {
        this.id_category = idCount++;
        this.name = name;
        this.id_store = id_store;
    }
}