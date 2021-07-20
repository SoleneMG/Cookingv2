package com.example.cookingv2.data.server.model.networkResponse;

public class NetworkResponseFailure<T, E> extends NetworkResponse<T, E> {
    public final E error;

    public NetworkResponseFailure(E error) {
        super(false);
        this.error = error;
    }
}
