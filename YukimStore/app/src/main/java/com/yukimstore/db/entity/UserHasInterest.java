package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "UserHasInterest",
        foreignKeys = {
                @ForeignKey(entity = Interest.class,
                        parentColumns = "id_interest",
                        childColumns = "id_interest"),
                @ForeignKey(entity = User.class,
                        parentColumns = "id_user",
                        childColumns = "id_user")
        })
public class UserHasInterest {
    @PrimaryKey
    public int id_uhi;

    @ColumnInfo(name = "id_interest", index = true)
    public int id_interest;

    @ColumnInfo(name = "id_user", index = true)
    public int id_user;

    private static int idCount = 0;

    public UserHasInterest(int id_interest, int id_user) {
        this.id_uhi = idCount++;
        this.id_interest = id_interest;
        this.id_user = id_user;
    }
}