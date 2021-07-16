package com.example.cookingv2;

import android.content.Context;

import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.retrofitImpl.CookingServerImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Inject {
    private static final CookingServer SERVER = new CookingServerImpl();
    private static CookingDatabase DATABASE;
    private static final int numberOfThread = 4;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(numberOfThread);

    public static void init(Context context) {
        DATABASE = CookingDatabase.getDatabase(context);
    }

    public static CookingDatabase getDatabase() {
        return DATABASE;
    }

    public static ExecutorService getExecutor() {
        return EXECUTOR;
    }

    public static CookingServer getServer() {
        return SERVER;
    }
}
