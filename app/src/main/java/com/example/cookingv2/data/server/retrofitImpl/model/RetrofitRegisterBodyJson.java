package com.example.cookingv2.data.server.retrofitImpl.model;

public class RetrofitRegisterBodyJson {
    public final String email, password, language;

    public RetrofitRegisterBodyJson(String email, String password, String language) {
        this.email = email;
        this.password = password;
        this.language = language;
    }
}
