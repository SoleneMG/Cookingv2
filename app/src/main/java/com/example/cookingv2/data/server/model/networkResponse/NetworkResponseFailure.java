package com.example.cookingv2.data.server.model.networkResponse;

import com.example.cookingv2.model.Error;

public class NetworkResponseFailure<T> extends NetworkResponse<T> {
    public final Error error;

    public NetworkResponseFailure(Error error) {
        super(false);
        this.error = error;
    }
}
