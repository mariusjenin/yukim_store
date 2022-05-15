package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProductInBasket",
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "id_product",
                        childColumns = "id_product"),
                @ForeignKey(entity = User.class,
                        parentColumns = "id_user",
                        childColumns = "id_user")
        })
public class ProductInBasket {
    @PrimaryKey
    public int id_pib;

    @ColumnInfo(name = "id_product", index = true)
    public int id_product;

    @ColumnInfo(name = "id_user", index = true)
    public int id_user;

    private static int idCount = 0;

    public ProductInBasket(int id_product, int id_user) {
        this.id_pib = idCount++;
        this.id_product = id_product;
        this.id_user = id_user;
    }
}