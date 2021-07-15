package com.example.cookingv2;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//todo + // c'moi qui ++++++++++
public class MyApplication extends Application {
    private static Context context;
    private static final int numberOfThread = 4;
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(numberOfThread);

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }

}
