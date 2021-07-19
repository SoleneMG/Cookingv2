package com.example.cookingv2.presentation.splashActivity;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

import java.util.List;

public interface StartLoadingCallBack {

    void onCompleteStartLoadingApplication(List<User> usersList);


}
