package com.example.cookingv2.data.server.model.login;

import com.example.cookingv2.data.server.model.UserJson;

public class LoginJson {

    public final UserJson user;
    public final String refreshToken;
    public final String accessToken;

    public LoginJson(UserJson user, String refreshToken, String accessToken) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
