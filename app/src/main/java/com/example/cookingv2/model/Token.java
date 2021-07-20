package com.example.cookingv2.model;

public class Token {

    public final String refreshToken;
    public final String accessToken;

    public Token(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
