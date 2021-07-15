package com.example.cookingv2.data.database;

import android.content.Context;

import com.example.cookingv2.data.database.impl.RoomImpl;

public interface CookingDatabase {

    static CookingDatabase getDatabase(Context context) {
        return RoomImpl.getInstance(context);
    }

    UserDao userDao();
}
