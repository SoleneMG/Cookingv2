package com.example.cookingv2.presentation.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.cookingv2.R;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.model.Error;
import com.example.cookingv2.model.Token;
import com.google.android.material.snackbar.Snackbar;

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

        this.fillEmailField();
    }

    public void onClickButtonLogin(View view) {
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        viewModel.sendPostLogin(emailString, passwordString, networkResponse -> {
            if (networkResponse != null) {
                if (networkResponse.isSuccess) {
                    makeSnackBar(view).setText("Successfull authentication").show();
                } else if (networkResponse instanceof NetworkResponseFailure)
                    displayErrorSnackBar(view, (NetworkResponseFailure<Token, Error.LoginError>) networkResponse);

            }
        });

    }

    private void fillEmailField() {
        Intent intent = getIntent();
        @SuppressWarnings("JavacQuirks") String userId = intent.getParcelableExtra("userId");
        viewModel.searchUserById(userId, user -> email.setText(user.email));
    }

    private void displayErrorSnackBar(View view, NetworkResponseFailure<Token, Error.LoginError> networkResponse) {
        switch (networkResponse.error) {
            case INVALID_EMAIL:
                makeSnackBar(view).setText(R.string.email_invalid).show();
                break;
            case INVALID_PASSWORD:
                makeSnackBar(view).setText(R.string.invalid_password).show();
                break;
            case USER_NOT_FOUND:
                makeSnackBar(view).setText(R.string.user_not_found).show();
                break;
            case PASSWORD_NOT_MATCH:
                makeSnackBar(view).setText(R.string.password_not_match).show();
                break;
            case UNEXPECTED_ERROR:
                makeSnackBar(view).show();
                break;
            default:
                throw new IllegalArgumentException("Untreated error" + networkResponse.error);
        }
    }

    private Snackbar makeSnackBar(View view) {
        return Snackbar.make(LoginActivity.this, view, getText(R.string.error_message), Snackbar.LENGTH_SHORT)
                .setAction("RETRY", this::onClickButtonLogin)
                .setBackgroundTint(getColor(R.color.design_default_color_primary))
                .setTextColor(getColor(R.color.design_default_color_on_primary))
                .setActionTextColor(getColor(R.color.design_default_color_on_primary));
    }
}