package com.example.cookingv2.data.server.model.networkResponse;

public class NetworkResponse<T, E> {
    public final boolean isSuccess;

    public NetworkResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
