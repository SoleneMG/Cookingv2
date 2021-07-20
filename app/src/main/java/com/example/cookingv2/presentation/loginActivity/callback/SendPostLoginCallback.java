package com.example.cookingv2.presentation.loginActivity.callback;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.Token;

public interface SendPostLoginCallback {

    void onCompleteSendPostLoginCallback(NetworkResponse<Token, Error.LoginError> networkResponse);
}
