package com.example.cookingv2.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import com.example.cookingv2.Inject;
import com.example.cookingv2.R;
import com.example.cookingv2.data.MyCallback;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SplashActivity extends AppCompatActivity {
    private final CookingDatabase DATABASE = Inject.getDatabase();
    private ExecutorService EXECUTOR = Inject.getExecutor();
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //todo à quoi sert l'interface si tu cast en RoomImpl ? // ok
        getUserList(roomUsersList -> {
            if (roomUsersList == null) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getUserList(MyCallback myCallback) {
        EXECUTOR.submit(() -> {
            if (!(DATABASE.userDao().getAll().isEmpty())) {
                List<RoomUser> roomUsersList = DATABASE.userDao().getAll();
                myHandler.post(() -> myCallback.onCompleteStartLoadingApplication(roomUsersList));
                Log.d("INFO", "Database doesn't empty"+ roomUsersList);
            } else {
                myHandler.post(() -> myCallback.onCompleteStartLoadingApplication(null));
                Log.d("INFO", "Database is empty. User doesn't register");
                //todo préciser pas d'user // ok
            }
        });
    }


}