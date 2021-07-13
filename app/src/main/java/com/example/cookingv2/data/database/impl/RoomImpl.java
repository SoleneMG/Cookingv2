package com.example.cookingv2.data.database.impl;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.UserDao;
import com.example.cookingv2.model.User;

@Database(entities = {User.class} , version = 1, exportSchema = false)
public abstract class RoomImpl extends RoomDatabase implements CookingDatabase {
    private static volatile RoomImpl INSTANCE;
    public abstract UserDao userDao();

    public static RoomImpl getInstance (final Context context){
        if (INSTANCE == null) {
            synchronized (RoomImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomImpl.class, "cookingDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}