package com.example.cookingv2.presentation.splashActivity;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

import java.util.List;

//todo ton callback se trouve dans le package bdd alors qu'il n'est pas utilisé dans aucune methode de bdd ou dao // ok
public interface StartLoadingCallBack {

    void onCompleteStartLoadingApplication(List<User> usersList);


}