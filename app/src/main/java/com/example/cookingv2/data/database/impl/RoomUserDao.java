package com.example.cookingv2.data.database.impl;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.example.cookingv2.model.User;

import java.util.List;

//todo si tu mets pas cette interface dans le package impl, elle devrait être indépendante de la techno // ok
//il vaut mieux soit faire qu'un seul RoomUserDao, ou alors une interface neutre utilisée par l'impl dao room

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
