package com.example.cookingv2.data.server.retrofitImpl;

import android.util.Log;

import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.UserJson;
import com.example.cookingv2.data.server.model.login.LoginBodyJson;
import com.example.cookingv2.data.server.model.login.LoginJson;
import com.example.cookingv2.data.server.model.login.LoginNetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.data.server.model.register.RegisterBodyJson;
import com.example.cookingv2.data.server.model.register.RegisterNetworkResponse;
import com.example.cookingv2.data.server.retrofitImpl.model.RetrofitErrorBody;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.Token;
import com.example.cookingv2.model.User;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CookingServerImpl implements CookingServer {
    private final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.17:8080/").addConverterFactory(GsonConverterFactory.create()).build();
    private final RetrofitCookingServer retrofitCookingServer = retrofit.create(RetrofitCookingServer.class);

    @Override
    public NetworkResponse<User, Error.RegisterError> sendPostRegister(String email, String password, String language) throws IOException {
        Call<RegisterNetworkResponse> call = retrofitCookingServer.postRegister(new RegisterBodyJson(email, password, language));
        Response<RegisterNetworkResponse> response = call.execute();
        if (response.isSuccessful()) {
            return treatRegisterNetworkResponseSuccess(response);
        } else {
            return treatRegisterNetworkResponseFailure(response);
        }
    }

    @Override
    public NetworkResponse<Token, Error.LoginError> sendPostLogin(String email, String password) throws IOException {
        Call<LoginNetworkResponse> call = retrofitCookingServer.postLogin(new LoginBodyJson(email, password));
        Response<LoginNetworkResponse> response = call.execute();
        if (response.isSuccessful()) {
            return treatLoginNetworkSuccess(response);
        } else {
            return treatLoginNetworkResponseFailure(response);
        }
    }


    private NetworkResponseSuccess<User, Error.RegisterError> treatRegisterNetworkResponseSuccess(Response<RegisterNetworkResponse> response) {
        UserJson userJson = response.body().data;
        User user = new User(userJson.publicId, userJson.id, userJson.email);
        return new NetworkResponseSuccess<>(user);
    }

    private NetworkResponseFailure<User, Error.RegisterError> treatRegisterNetworkResponseFailure(Response<RegisterNetworkResponse> response) {
        try {
            assert response.errorBody() != null;
            RetrofitErrorBody retrofitErrorBody = new Gson().fromJson(response.errorBody().string(), RetrofitErrorBody.class);
            switch (retrofitErrorBody.errorJson.reasonCode) {
                case "IN001":
                    return new NetworkResponseFailure<>(Error.RegisterError.INVALID_EMAIL);
                case "IN002":
                    return new NetworkResponseFailure<>(Error.RegisterError.INVALID_PASSWORD);
                case "IN003":
                    return new NetworkResponseFailure<>(Error.RegisterError.INVALID_LANGUAGE);
                case "US001":
                    return new NetworkResponseFailure<>(Error.RegisterError.USER_ALREADY_EXIST);
                case "AP001":
                    return new NetworkResponseFailure<>(Error.RegisterError.UNEXPECTED_ERROR);
                default:
                    throw new IllegalArgumentException("Error not supported" + retrofitErrorBody.errorJson.reasonCode);
            }
        } catch (IOException e) {
            Log.d("DEBUG", "Network error " + e);
            return new NetworkResponseFailure<>(Error.RegisterError.UNEXPECTED_ERROR);
        }
    }

    private NetworkResponseSuccess<Token, Error.LoginError> treatLoginNetworkSuccess(Response<LoginNetworkResponse> response) {
        LoginJson loginJson = response.body().data;
        Token token = new Token(loginJson.refreshToken, loginJson.accessToken);
        return new NetworkResponseSuccess<>(token);
    }

    private NetworkResponseFailure<Token, Error.LoginError> treatLoginNetworkResponseFailure(Response<LoginNetworkResponse> response) {
        try {
            RetrofitErrorBody retrofitErrorBody = new Gson().fromJson(response.errorBody().string(), RetrofitErrorBody.class);
            switch (retrofitErrorBody.errorJson.reasonCode) {
                case "IN001":
                    return new NetworkResponseFailure<>(Error.LoginError.INVALID_EMAIL);
                case "IN002":
                    return new NetworkResponseFailure<>(Error.LoginError.INVALID_PASSWORD);
                case "US002":
                    return new NetworkResponseFailure<>(Error.LoginError.USER_NOT_FOUND);
                case "AU006":
                    return new NetworkResponseFailure<>(Error.LoginError.PASSWORD_NOT_MATCH);
                case "AP001":
                    return new NetworkResponseFailure<>(Error.LoginError.UNEXPECTED_ERROR);
                default:
                    throw new IllegalArgumentException("Error not supported" + retrofitErrorBody.errorJson.reasonCode);
            }
        } catch (IOException e) {
            Log.d("DEBUG", "Network error " + e);
            return new NetworkResponseFailure<>(Error.LoginError.UNEXPECTED_ERROR);

        }
    }

}
