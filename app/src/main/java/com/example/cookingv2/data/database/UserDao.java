package com.example.cookingv2.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cookingv2.model.User;

import java.util.List;

//todo si tu mets pas cette interface dans le package impl, elle devrait être indépendante de la techno
//il vaut mieux soit faire qu'un seul RoomUserDao, ou alors une interface neutre utilisée par l'impl dao room
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
