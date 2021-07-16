package com.example.cookingv2.presentation.splashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import com.example.cookingv2.Inject;
import com.example.cookingv2.R;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.model.User;
import com.example.cookingv2.presentation.LoginActivity;
import com.example.cookingv2.presentation.RegisterActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SplashActivity extends AppCompatActivity {
    private final ExecutorService EXECUTOR = Inject.getExecutor();
    private final CookingDatabase DATABASE = Inject.getDatabase();
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getUserList(usersList -> {
            if (usersList == null) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getUserList(StartLoadingCallBack callback) {
        EXECUTOR.submit(() -> {
            List<User> usersList = DATABASE.userDao().getAll();
            if (!(usersList.isEmpty())) {
                myHandler.post(() -> callback.onCompleteStartLoadingApplication(usersList));
                Log.d("INFO", "Database doesn't empty" + usersList);
            } else {
                myHandler.post(() -> callback.onCompleteStartLoadingApplication(null));
                Log.d("INFO", "Database is empty. No registered user");
            }
        });
    }

}
