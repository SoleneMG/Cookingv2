package com.example.cookingv2.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingv2.Inject;
import com.example.cookingv2.R;
import com.example.cookingv2.data.MyCallback;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final ExecutorService EXECUTOR = Inject.getExecutor();
    private final CookingDatabase DATABASE = Inject.getDatabase();
    private final CookingServer server = Inject.getServer();
    private EditText email, password;
    private Spinner spinner;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //TESTING
        email.setText(R.string.email_test);
        password.setText(R.string.password_test);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_label_spinner, R.layout.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }
    }


    public void onClickButtonRegister(View view) {
        String emailString, passwordString;
        emailString = email.getText().toString();
        passwordString = password.getText().toString();
        server.sendPostRegister(emailString, passwordString, language, new MyCallback() {
            @Override
            public void onCompleteStartLoadingApplication(List<User> usersList) {
                // Do Nothing
            }

            @Override
            public void onCompleteSendPostRegister(NetworkResponse<User> networkResponse) {
                if (networkResponse != null) {
                    if (networkResponse instanceof NetworkResponseFailure) {
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
                        User user = ((NetworkResponseSuccess<User>) networkResponse).data;
                        EXECUTOR.submit(() -> DATABASE.userDao().insert(user));
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("userId", user.id);
                        startActivity(intent);
                    }
                } else {
                    displaySnackBar(view).show();
                }
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
        switch (position) {
            case 0:
                language = "fr";
                break;
            case 1:
                language = "en";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}