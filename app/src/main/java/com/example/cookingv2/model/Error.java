package com.example.cookingv2.model;

public class Error<E> {
    public final E error;

    public Error(E error) {
        this.error = error;
    }

    public enum RegisterError{
        INVALID_EMAIL,
        INVALID_PASSWORD,
        INVALID_LANGUAGE,
        USER_ALREADY_EXIST,
        UNEXPECTED_ERROR
    }

    public enum LoginError{
        INVALID_EMAIL,
        INVALID_PASSWORD,
        USER_NOT_FOUND,
        PASSWORD_NOT_MATCH,
        UNEXPECTED_ERROR
    }

}
