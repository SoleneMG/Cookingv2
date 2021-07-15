package com.example.cookingv2.data;

import com.example.cookingv2.data.database.impl.model.RoomUser;
import com.example.cookingv2.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public interface MyCallback {
    void onCompleteStartLoadingApplication(List<User> usersList);

    void onCompleteSendPostRegister();
}
