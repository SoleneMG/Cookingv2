package com.example.cookingv2.data.server.retrofitImpl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;

import com.example.cookingv2.Inject;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.UserJson;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CookingServerImpl implements CookingServer {
    private final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.17:8080/").addConverterFactory(GsonConverterFactory.create()).build();
    private final RetrofitCookingServer retrofitCookingServer = retrofit.create(RetrofitCookingServer.class);

    @Override
    public NetworkResponse<User> sendPostRegister(String email, String password, String language) throws IOException {
        Call<RegisterNetworkResponse> call = retrofitCookingServer.postRegister(new RetrofitRegisterBodyJson(email, password, language));
        Response<RegisterNetworkResponse> response = call.execute();
        if (response.isSuccessful()) {
            UserJson userJson = response.body().data;
            User user = new User(userJson.publicId, userJson.id, userJson.email);
            return new NetworkResponseSuccess<>(user);
        } else {
            RetrofitErrorBody retrofitErrorBody;
            try {
                retrofitErrorBody = new Gson().fromJson(response.errorBody().string(), RetrofitErrorBody.class);
                switch (retrofitErrorBody.errorJson.reasonCode) {
                    case "IN001":
                        return new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_EMAIL));
                    case "IN002":
                        return new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_PASSWORD));
                    case "IN003":
                        return new NetworkResponseFailure<>(new Error(Error.RegisterError.INVALID_LANGUAGE));
                    case "US001":
                        return new NetworkResponseFailure<>(new Error(Error.RegisterError.USER_ALREADY_EXIST));
                    case "AP001":
                        return new NetworkResponseFailure<>(new Error(Error.RegisterError.UNEXPECTED_ERROR));
                    default:
                        //todo ça pète ton app si ça throw ici ? // ok je sais pas je peux pas tester le cas, si?
                        throw new IllegalArgumentException("Error not supported" + retrofitErrorBody.errorJson.reasonCode);
                }
            } catch (IOException e) {
                Log.d("DEBUG", "Network error "+ e);
                return new NetworkResponseFailure<>(new Error(Error.RegisterError.UNEXPECTED_ERROR));

            }
        }
    }
}
