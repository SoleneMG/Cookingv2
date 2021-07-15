package com.example.cookingv2;

import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.impl.RoomImpl;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.retrofitImpl.RetrofitImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inject {

    private static final CookingServer SERVER = new RetrofitImpl();
    private static final CookingDatabase DATABASE = CookingDatabase.getDatabase(MyApplication.getAppContext());
    private static final int numberOfThread = 4;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(numberOfThread);


    public static CookingDatabase getDatabase(){return DATABASE;}

    public static ExecutorService getExecutor(){return EXECUTOR;}

    public static CookingServer getServer(){return SERVER;}
}
