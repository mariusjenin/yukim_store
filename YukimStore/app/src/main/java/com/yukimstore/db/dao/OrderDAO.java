package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Offer;
import com.yukimstore.db.entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_order = :id LIMIT 1")
    Order get(int id);

    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_user = :id_user and OrderTable.id_store = :id_store")
    List<Order> get(int id_user, int id_store);

    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_user = :id_user")
    List<Order> getWithUser(int id_user);

    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_store = :id_store")
    List<Order> getWithStore(int id_store);

    @Insert(onConflict = ABORT)
    long insert(Order order);

    @Insert(onConflict = ABORT)
    void insertAll(List<Order> orders);

    @Query("DELETE FROM OrderTable")
    void clear();
}
