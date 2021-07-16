package com.example.cookingv2.data.server;

import com.example.cookingv2.data.database.StartLoadingCallBack;

public interface CookingServer {

    void sendPostRegister(String email, String password, String language, RegisterSendPostCallBack callback);
}
