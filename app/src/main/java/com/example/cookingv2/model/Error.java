package com.example.cookingv2.model;

public class Error {
    public final RegisterError registerError;

    public Error(RegisterError registerError) {
        this.registerError = registerError;
    }

    public enum RegisterError{
        INVALID_EMAIL,
        INVALID_PASSWORD,
        INVALID_LANGUAGE,
        USER_ALREADY_EXIST,
        UNEXPECTED_ERROR
    }

}
