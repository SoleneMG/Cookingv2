package com.example.cookingv2.data.database;

import android.content.Context;

import com.example.cookingv2.data.database.impl.RoomImpl;
import com.example.cookingv2.data.database.impl.RoomUserDao;

public interface CookingDatabase {

    static CookingDatabase getDatabase(Context context) {
        return RoomImpl.getInstance(context);
    }

    /**
     * todo CookingDatabase est ton interface neutre, c'est à dire que tous ceux qui l'utilisent ne doivent pas savoir
     * quelle est la techno derrière. Cela permet de changer de techno facilement sans impacter tout le code.
     * Ici tu retournes une RoomUserDao, donc une classe dédiée Room, ton interface CookingDatabase perd de son interet.
     */
    RoomUserDao userDao();

}
