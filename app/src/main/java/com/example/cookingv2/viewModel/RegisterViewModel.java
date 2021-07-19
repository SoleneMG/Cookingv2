package com.example.cookingv2.viewModel;


import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import com.example.cookingv2.Inject;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.RegisterSendPostCallBack;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.model.User;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class RegisterViewModel {
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private final CookingDatabase database = Inject.getDatabase();
    private final ExecutorService executor = Inject.getExecutor();
    private final CookingServer server = Inject.getServer();

    public void sendPostRegister(String email, String password, String language, RegisterSendPostCallBack callback) {
        executor.submit(() -> {
            try {
                NetworkResponse<User> networkResponse = server.sendPostRegister(email,password,language);
                if(networkResponse instanceof NetworkResponseSuccess){
                    User user = ((NetworkResponseSuccess<User>) networkResponse).data;
                    database.userDao().insert(user);
                    myHandler.post(() -> callback.onCompleteSendPostRegister(networkResponse));
                }
                myHandler.post(() -> callback.onCompleteSendPostRegister(networkResponse));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }
}