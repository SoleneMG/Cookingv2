package com.example.cookingv2;

import android.app.Application;
import android.content.Context;

import com.example.cookingv2.data.database.CookingDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//todo + // c'moi qui ++++++++++
public class MyApplication extends Application {
    CookingDatabase database = Inject.getDatabase();
    ExecutorService executor = Inject.getExecutor();

    @Override
    public void onCreate() {
        super.onCreate();
        Inject.init(this);

    }

}
