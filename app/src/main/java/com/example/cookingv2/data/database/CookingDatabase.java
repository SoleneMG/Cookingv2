package com.example.cookingv2.data.database;

import android.content.Context;

import com.example.cookingv2.data.database.impl.RoomImpl;
import com.example.cookingv2.data.database.impl.RoomUserDao;

/**
 * todo à quoi sert l'interface ? pas de méthodes // ok
 */
public interface CookingDatabase {

    static CookingDatabase getDatabase(Context context) {
        return RoomImpl.getInstance(context);
    }

    RoomUserDao userDao();

}
