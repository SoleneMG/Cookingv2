package com.example.cookingv2.data.server.model;

public class TokenJson {

    public final String refreshToken;
    public final String accessToken;

    public TokenJson(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
