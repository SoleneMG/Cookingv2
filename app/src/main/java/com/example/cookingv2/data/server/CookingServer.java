package com.example.cookingv2.data.server;

import com.example.cookingv2.data.MyCallback;

public interface CookingServer {

    void sendPostRegister(String email, String password, String language, MyCallback myCallback);
}
