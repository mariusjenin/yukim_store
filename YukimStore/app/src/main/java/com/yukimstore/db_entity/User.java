package com.yukimstore.db_entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey
    public int id_user;

    @ColumnInfo(name = "mail")
    public String mail;

    @ColumnInfo(name = "hash_pwd")
    public String hash_pwd;

    @ColumnInfo(name = "is_merchant")
    public boolean is_merchant;

    private static int idCount = 0;

    public User(String mail,String hash_pwd,boolean is_merchant) {
        this.id_user = idCount++;
        this.mail = mail;
        this.hash_pwd = hash_pwd;
        this.is_merchant = is_merchant;
    }
}
