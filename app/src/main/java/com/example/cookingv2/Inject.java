package com.example.cookingv2;

import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.impl.RoomImpl;

public class Inject {

    private static final CookingDatabase DATABASE = RoomImpl.getInstance(new MyApplication().context);

    public CookingDatabase getDatabase(){return DATABASE;}
}
