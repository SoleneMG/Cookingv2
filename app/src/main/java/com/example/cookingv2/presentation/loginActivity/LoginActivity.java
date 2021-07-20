package com.example.cookingv2.presentation.loginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cookingv2.R;

public class LoginActivity extends AppCompatActivity {
    private LoginActitivyViewModel viewModel;
    private TextView email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginActitivyViewModel.class);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void onClickButtonLogin(View view) {
    }
}