package com.example.cookingv2.presentation.registerActivity;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.User;

public interface RegisterCallBack {

    void onCompleteSendPostRegister(NetworkResponse<User, Error.RegisterError> networkResponse);
}
