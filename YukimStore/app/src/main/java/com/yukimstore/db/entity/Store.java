package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Store",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id_user",
                childColumns = "id_user_store"))
public class Store {
    @PrimaryKey
    public int id_user_store;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "img_store")
    public String img_store;

    private static int idCount = 0;

    public Store(String name, String img_store) {
        this.id_user_store = idCount++;
        this.name = name;
        this.img_store = img_store;
    }
}
