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
import com.example.cookingv2.MyApplication;
import com.example.cookingv2.R;
import com.example.cookingv2.data.MyCallback;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SplashActivity extends AppCompatActivity {
    private final CookingDatabase DATABASE = Inject.getDatabase();
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getUserList(new MyCallback() {
                        @Override
                        public void onCompleteStartLoadingApplication(List<User> usersList) {
                            if (usersList == null) {
                                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

            @Override
            public void onCompleteSendPostRegister(NetworkResponse<User> networkResponse) {
                // DO NOTHING
            }
                    });
    }

    public void getUserList(MyCallback myCallback) {
        Inject.getExecutor().submit(() -> {
            if (!(DATABASE.userDao().getAll().isEmpty())) {
                List<User> usersList = DATABASE.userDao().getAll();
                myHandler.post(() -> myCallback.onCompleteStartLoadingApplication(usersList));
                Log.d("INFO", "Database doesn't empty"+ usersList);
            } else {
                myHandler.post(() -> myCallback.onCompleteStartLoadingApplication(null));
                Log.d("INFO", "Database is empty. User doesn't register");
            }
        });
    }


}