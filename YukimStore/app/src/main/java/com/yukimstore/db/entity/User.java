package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yukimstore.utils.HashUtil;

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

    @ColumnInfo(name = "token_connect")
    public String token_connect;

    //CUSTOMER
    @ColumnInfo(name = "first_name")
    public String first_name;

    @ColumnInfo(name = "last_name")
    public String last_name;

    private static int idCount = 0;

    public User(String mail,String hash_pwd,boolean is_merchant, String first_name, String last_name) {
        this.id_user = idCount++;
        this.mail = mail;
        this.hash_pwd = hash_pwd;
        this.is_merchant = is_merchant;
        this.first_name = first_name;
        this.last_name = last_name;
        this.token_connect = HashUtil.randomString(128);
    }
}
