package com.example.cookingv2.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingv2.Inject;
import com.example.cookingv2.R;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.model.User;
import com.example.cookingv2.viewModel.RegisterViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final RegisterViewModel registerViewModel = new RegisterViewModel();
    private EditText email, password;
    private Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //TESTING
        email.setText("email@test.fr");
        password.setText("password");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_label_spinner, R.layout.spinner);

        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);
    }


    public void onClickButtonRegister(View view) {
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String language;
        int position = spinner.getSelectedItemPosition();
        if (position == 0) {
            language = "fr";
        } else {
            language = "en";
        }

        registerViewModel.sendPostRegister(emailString, passwordString, language, networkResponse -> {
            if (networkResponse != null) {
                if (networkResponse instanceof NetworkResponseFailure) {
                    //todo tu peux faire une méthode displaysnackbar pour séparer
                    switch (((NetworkResponseFailure<User>) networkResponse).error.registerError) {
                        case INVALID_EMAIL:
                            displaySnackBar(view).setText(R.string.email_invalid).show();
                            break;
                        case INVALID_PASSWORD:
                            displaySnackBar(view).setText(R.string.invalid_password).show();
                            break;
                        case INVALID_LANGUAGE:
                            displaySnackBar(view).setText(R.string.invalid_language).show();
                            break;
                        case USER_ALREADY_EXIST:
                            displaySnackBar(view).setText(R.string.user_already_exist).show();
                            break;
                        case UNEXPECTED_ERROR:
                            displaySnackBar(view).show();
                            break;
                        default:
                            throw new IllegalArgumentException("Untreated Error :" + ((NetworkResponseFailure<User>) networkResponse).error.registerError);
                    }
                } else {
                    //todo de même une méthode startLogin
                    User user = ((NetworkResponseSuccess<User>) networkResponse).data;
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("userId", user.id);
                    startActivity(intent);
                }
            } else {
                displaySnackBar(view).show();
            }
        });
    }

    private Snackbar displaySnackBar(View view) {
        return Snackbar.make(RegisterActivity.this, view, getText(R.string.error_message), Snackbar.LENGTH_INDEFINITE)
                .setAction("Try again", v -> onClickButtonRegister(view))
                .setBackgroundTint(getColor(R.color.design_default_color_primary))
                .setTextColor(getColor(R.color.design_default_color_on_primary))
                .setActionTextColor(getColor(R.color.design_default_color_on_primary));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}