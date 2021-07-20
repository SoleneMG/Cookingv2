package com.example.cookingv2.data.server.model.login;

import com.example.cookingv2.data.server.model.ErrorJson;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;

public class LoginNetworkResponse {

public final LoginJson data;
public final ErrorJson errorJson;

    public LoginNetworkResponse(LoginJson data, ErrorJson errorJson) {
        this.data = data;
        this.errorJson = errorJson;
    }
}
