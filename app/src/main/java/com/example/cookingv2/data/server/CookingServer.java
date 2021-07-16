package com.example.cookingv2.data.server;

public interface CookingServer {

    void sendPostRegister(String email, String password, String language, RegisterSendPostCallBack callback);
}
