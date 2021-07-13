package com.example.cookingv2.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cookingv2.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("DELETE FROM user")
    void delete();

    @Query("SELECT * FROM user WHERE id LIKE :userId")
    User findUserById(String userId);

}
