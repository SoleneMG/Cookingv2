package com.example.cookingv2.data.server;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

public interface RegisterSendPostCallBack {

    void onCompleteSendPostRegister(NetworkResponse<User> networkResponse);
}
