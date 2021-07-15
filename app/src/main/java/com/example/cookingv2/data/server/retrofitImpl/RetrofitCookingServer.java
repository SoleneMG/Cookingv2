package com.example.cookingv2.data.server.retrofitImpl;

import com.example.cookingv2.data.server.model.register.RegisterNetworkResponse;
import com.example.cookingv2.data.server.retrofitImpl.model.RetrofitRegisterBodyJson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitCookingServer {

    @POST("api/1/register")
    Call<RegisterNetworkResponse> postRegister(@Body RetrofitRegisterBodyJson retrofitRegisterBodyJson);
}
