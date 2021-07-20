package com.example.cookingv2.data.server.model.register;

public class RegisterBodyJson {
    public final String email, password, language;

    public RegisterBodyJson(String email, String password, String language) {
        this.email = email;
        this.password = password;
        this.language = language;
    }
}
