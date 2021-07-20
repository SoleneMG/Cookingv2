package com.example.cookingv2.data.server;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.Token;
import com.example.cookingv2.model.User;

import java.io.IOException;

public interface CookingServer {

    NetworkResponse<User, Error.RegisterError> sendPostRegister(String email, String password, String language) throws IOException;

    NetworkResponse<Token, Error.LoginError> sendPostLogin(String email, String password) throws IOException;
}
