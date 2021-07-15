package com.example.cookingv2.data.database.impl;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.example.cookingv2.model.User;

import java.util.List;

@Dao
public interface RoomUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RoomUser user);

    @Query("SELECT * FROM user")
    List<RoomUser> getAll();

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user WHERE id LIKE :userId")
    RoomUser findUserById(String userId);

}
