package com.example.cookingv2.data.server.model.register;

import com.example.cookingv2.data.server.model.UserJson;

public class RegisterNetworkResponse {
    public final UserJson data;

    public RegisterNetworkResponse(UserJson data) {
        this.data = data;
    }
}

