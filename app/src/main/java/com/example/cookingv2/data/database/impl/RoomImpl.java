package com.example.cookingv2.data.database.impl;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookingv2.data.database.CookingDatabase;

public abstract class RoomImpl extends RoomDatabase implements CookingDatabase {
    private static volatile RoomImpl INSTANCE;

    public static RoomImpl getInstance (final Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomImpl.class, "cookingDatabase").build();
        }
        return INSTANCE;
    }
}
