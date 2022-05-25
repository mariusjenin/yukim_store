package com.yukimstore.db.dao;

import static androidx.room.OnConflictStrategy.ABORT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.yukimstore.db.entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_order = :id LIMIT 1")
    Order get(int id);

    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_user = :id_user")
    List<Order> getWithUser(int id_user);

    @Query("SELECT * FROM OrderTable WHERE OrderTable.id_store = :id_store")
    List<Order> getWithStore(int id_store);

    @Insert(onConflict = ABORT)
    void insert(Order order);

    @Query("DELETE FROM OrderTable")
    void clear();
}
