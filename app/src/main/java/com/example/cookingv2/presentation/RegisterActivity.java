package com.example.cookingv2.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.cookingv2.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //todo vaut mieux l'appeler du style onClickButtonRegister par exemple pour qu'on comprenne à quoi sert la méthode // ok
    public void onClickButtonRegister(View view) {
    }
}