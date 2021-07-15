package com.example.cookingv2.data.server.retrofitImpl;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;

import com.example.cookingv2.data.MyCallback;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.UserJson;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.data.server.model.register.RegisterNetworkResponse;
import com.example.cookingv2.data.server.retrofitImpl.model.RetrofitErrorBody;
import com.example.cookingv2.data.server.retrofitImpl.model.RetrofitRegisterBodyJson;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.User;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.cookingv2.MyApplication.EXECUTOR;


public class RetrofitImpl implements CookingServer {
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.17:8080/").addConverterFactory(GsonConverterFactory.create()).build();
    private static final RetrofitCookingServer RETROFIT_COOKING_SERVER = retrofit.create(RetrofitCookingServer.class);
    private final Handler myHandler = HandlerCompat.createAsync(Looper.getMainLooper());


    @Override
    public void sendPostRegister(String email, String password, String language, MyCallback myCallback) {
        EXECUTOR.submit(() -> {
            Call<RegisterNetworkResponse> call = RETROFIT_COOKING_SERVER.postRegister(new RetrofitRegisterBodyJson(email, password, language));
            call.enqueue(new Callback<RegisterNetworkResponse>() {
                @Override
                public void onResponse(@NonNull Call<RegisterNetworkResponse> call, @NonNull Response<RegisterNetworkResponse> response) {
                    if (response.body() != null && response.errorBody() == null && response.isSuccessful()) {
                        myHandler.post(() -> {
                            UserJson userJson = response.body().data;
                            myCallback.onCompleteSendPostRegister(new NetworkResponseSuccess<>(new User(userJson.publicId, userJson.id, userJson.email)));
                        });

                    } else {
                        RetrofitErrorBody retrofitErrorBody = null;
                        try {
                            retrofitErrorBody = new Gson().fromJson(response.errorBody().string(), RetrofitErrorBody.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        switch (retrofitErrorBody.errorJson.reasonCode) {
                            case "IN001":
                                myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_EMAIL))));
                                break;
                            case "IN002":
                                myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_PASSWORD))));
                                break;
                            case "IN003":
                                myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_LANGUAGE))));
                                break;
                            case "US001":
                                myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.USER_ALREADY_EXIST))));
                                break;
                            case "AP001":
                                myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.UNEXPECTED_ERROR))));
                                break;
                            default:
                                throw new IllegalArgumentException("Error not supported" + retrofitErrorBody.errorJson.reasonCode);

                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RegisterNetworkResponse> call, @NonNull Throwable t) {
                    myHandler.post(() -> myCallback.onCompleteSendPostRegister(new NetworkResponseFailure<>(new Error(Error.RegisterError.UNEXPECTED_ERROR))));

                }
            });
        });
    }
}
