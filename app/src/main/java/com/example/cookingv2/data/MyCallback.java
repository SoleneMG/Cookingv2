package com.example.cookingv2.data;

import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.model.User;

import java.util.List;

//todo là ton callback sert pour la bdd + réseau, il va bien grossir comme Evan, vaut mieux en faire un par méthode
public interface MyCallback {
    void onCompleteStartLoadingApplication(List<User> usersList);

    void onCompleteSendPostRegister(NetworkResponse<User> networkResponse);
}
