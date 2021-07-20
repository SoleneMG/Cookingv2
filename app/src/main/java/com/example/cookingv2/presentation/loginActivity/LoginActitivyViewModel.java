package com.example.cookingv2.presentation.loginActivity;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.ViewModel;

import com.example.cookingv2.Inject;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.Token;
import com.example.cookingv2.model.User;
import com.example.cookingv2.presentation.loginActivity.callback.SearchUserByIdCallBack;
import com.example.cookingv2.presentation.loginActivity.callback.SendPostLoginCallback;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class LoginActitivyViewModel extends ViewModel {
    private final CookingServer server = Inject.getServer();
    private final CookingDatabase database = Inject.getDatabase();
    private final ExecutorService executor = Inject.getExecutor();
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    public void searchUserById(String userId, SearchUserByIdCallBack callBack){
        executor.submit(() -> {
            User user = database.userDao().findUserById(userId);
            handler.post(() -> callBack.onCompleteFindUserById(user));
        });
    }

    public void sendPostLogin(String email, String password, SendPostLoginCallback callback){
        executor.submit(() -> {
            try {
               NetworkResponse<Token, Error.LoginError> response = server.sendPostLogin(email, password);
                if(response instanceof NetworkResponseSuccess){
                    User user = database.userDao().findUserByEmail(email);
                    database.tokenDao().insert(user.publicId,((NetworkResponseSuccess<Token, Error.LoginError>) response).data);
                }
                handler.post(() -> callback.onCompleteSendPostLoginCallback(response));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
