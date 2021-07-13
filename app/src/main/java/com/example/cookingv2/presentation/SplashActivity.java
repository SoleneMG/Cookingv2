package com.example.cookingv2.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.cookingv2.Inject;
import com.example.cookingv2.R;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.UserDao;
import com.example.cookingv2.data.database.impl.RoomImpl;

import java.util.concurrent.ExecutorService;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private final CookingDatabase DATABASE = Inject.getDatabase();
    private UserDao userDao;
    private ExecutorService EXECUTOR = Inject.getExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progress_bar);
        userDao = ((RoomImpl)DATABASE).userDao();

        EXECUTOR.submit(() -> {
            if(userDao.getAll().isEmpty()){
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                Log.d("INFO", "Database is empty");
            } else {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                Log.d("INFO", "Database doesn't empty");
            }
        });
    }
}