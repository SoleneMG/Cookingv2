package com.example.cookingv2.data.server.model.login;

public class LoginBodyJson {

    public final String email, password;

    public LoginBodyJson(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
