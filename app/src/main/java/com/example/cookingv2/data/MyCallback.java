package com.example.cookingv2.data;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

import java.util.List;

public interface MyCallback {
    void onCompleteStartLoadingApplication(List<User> usersList);

    void onCompleteSendPostRegister(NetworkResponse<User> networkResponse);
}
