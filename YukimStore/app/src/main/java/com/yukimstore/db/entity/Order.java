package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "OrderTable",
        foreignKeys = {
                @ForeignKey(entity = Store.class,
                        parentColumns = "id_user_store",
                        childColumns = "id_store"),
                @ForeignKey(entity = User.class,
                        parentColumns = "id_user",
                        childColumns = "id_user")
        })
public class Order {
    @PrimaryKey
    public int id_order;

    @ColumnInfo(name = "id_store", index = true)
    public int id_store;

    @ColumnInfo(name = "id_user", index = true)
    public int id_user;

    @ColumnInfo(name = "date_order")
    public Date date_order;

    private static int idCount = 0;

    public Order(int id_store, int id_user, Date date_order) {
        this.id_order = idCount++;
        this.id_store = id_store;
        this.id_user = id_user;
        this.date_order = date_order;
    }
}