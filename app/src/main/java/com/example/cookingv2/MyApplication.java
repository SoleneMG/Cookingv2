package com.example.cookingv2;

import android.app.Application;
import android.content.Context;

import com.example.cookingv2.data.database.CookingDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Inject.init(this);

    }

}
