package com.yukimstore.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ProductInOrder",
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "id_product",
                        childColumns = "id_product"),
                @ForeignKey(entity = Order.class,
                        parentColumns = "id_order",
                        childColumns = "id_order")
        })
public class ProductInOrder implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id_pio;

    @ColumnInfo(name = "id_order", index = true)
    public int id_order;

    @ColumnInfo(name = "id_product", index = true)
    public int id_product;

    @ColumnInfo(name = "quantity")
    public int quantity;

    public ProductInOrder(int id_order, int id_product, int quantity) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.quantity = quantity;
    }
}