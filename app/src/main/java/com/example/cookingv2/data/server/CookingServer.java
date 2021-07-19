package com.example.cookingv2.data.server;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

import java.io.IOException;

public interface CookingServer {

    NetworkResponse<User> sendPostRegister(String email, String password, String language) throws IOException;
}
