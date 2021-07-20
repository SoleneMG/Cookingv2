package com.example.cookingv2.data.server.model.networkResponse;

public class NetworkResponseSuccess<T, E> extends NetworkResponse<T, E>{
    public final T data;

    public NetworkResponseSuccess(T data) {
        super(true);
        this.data = data;
    }
}
