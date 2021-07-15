package com.example.cookingv2.data.server.retrofitImpl.model;

import com.example.cookingv2.data.server.model.ErrorJson;
import com.google.gson.annotations.SerializedName;

public class RetrofitErrorBody {

    @SerializedName("error")
    public final ErrorJson errorJson;

    public RetrofitErrorBody(ErrorJson errorJson) {
        this.errorJson = errorJson;
    }
}
