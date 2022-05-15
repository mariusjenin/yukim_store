package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Interest")
public class Interest {
    @PrimaryKey
    public int id_interest;

    @ColumnInfo(name = "name")
    public String name;

    private static int idCount = 0;

    public Interest(String name) {
        this.id_interest = idCount++;
        this.name = name;
    }
}