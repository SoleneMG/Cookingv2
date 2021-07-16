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
import com.example.cookingv2.data.database.StartLoadingCallBack;
import com.example.cookingv2.data.database.CookingDatabase;
import com.example.cookingv2.data.server.CookingServer;
import com.example.cookingv2.data.server.RegisterSendPostCallBack;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponse;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseFailure;
import com.example.cookingv2.data.server.model.networkResponse.NetworkResponseSuccess;
import com.example.cookingv2.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //todo ça reste des variables pas des constantes, on écrit en minuscule // ok
    private final ExecutorService executor = Inject.getExecutor();
    private final CookingDatabase database = Inject.getDatabase();
    private final CookingServer server = Inject.getServer();
    private EditText email, password;
    private Spinner spinner;
    //todo vaut mieux éviter de faire une variable pour garder la données, car dans le cas d'une rotation ou autre ça pourrait poser probleme
    //todo tu peux faire comme l'email et mdp, tu récupère la position de l'élement selectionné du spinner et en déduire sa valeur // ok pas sûre que j'ai corrigé comme tu voulais

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //TESTING
        //todo tu peux mettre des string en dur pour les tests, ça éviter de nettoyer partout ensuite // ok mais y'a du jaune après tu vas me taper
        email.setText("email@test.fr");
        password.setText("password");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_label_spinner, R.layout.spinner);
        //todo pourquoi le spinner serait null ? // ok
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);
    }


    public void onClickButtonRegister(View view) {
        //todo tu peux écrire ces deux lignes sur la même au dessus // ok
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String language;
        int position = spinner.getSelectedItemPosition();
        if (position == 0) {
            language = "fr";
        } else {
            language = "en";
        }

        server.sendPostRegister(emailString, passwordString, language, networkResponse -> {
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
                    //todo tu lances une requête en async, récupère le résultat en sync, relance l'insertion en async pour continuer le changement d'activité en sync
                    //todo il faudrait tout faire en async et avoir qu'un seul callback qui permet de revenir au thread ui
                    executor.submit(() -> database.userDao().insert(user));
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