package com.example.cookingv2;

import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.retrofitImpl.RetrofitImpl;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class RetrofitImplTest {
@Mock
    CookingServer cookingServer;
@InjectMocks
    RetrofitImpl retrofitImpl;


    @Test
    public void sendPostRegister(){}
}
