package com.example.cookingv2.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cookingv2.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password;
    private Spinner language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        language = findViewById(R.id.spinner);
    }

    public void onClickButtonRegister(View view) {
        String emailString, passwordString, languageString;
        emailString = email.getText().toString();
        passwordString = password.getText().toString();
        languageString = language.getSelectedItem().toString();

    }
}