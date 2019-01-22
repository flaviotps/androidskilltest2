package com.flaviotps.cinq.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.interfaces.IAuthenticationPresenter;
import com.flaviotps.cinq.model.UserModel;
import com.flaviotps.cinq.presenter.AuthenticationPresenter;


public class LoginActivity extends AppCompatActivity implements IAuthenticationPresenter.View, View.OnClickListener, TextWatcher {

    private AuthenticationPresenter authenticationPresenter;
    private Button loginButton;
    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    private EditText editTextEmail, editTextPassword;
    private TextView textViewSignUp;

    private void ViewBind() {

        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(this);

        textInputLayoutEmail = findViewById(R.id.editTextEmailLayout);
        textInputLayoutPassword = findViewById(R.id.editTextPasswordLayout);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.addTextChangedListener(this);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.addTextChangedListener(this);

        textViewSignUp = findViewById(R.id.txtRegister);
        textViewSignUp.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authenticationPresenter = new AuthenticationPresenter(this, this);


        ViewBind();

        UserModel userSession = authenticationPresenter.getLocalSession();
        if (userSession.getId() > 0) {
            authenticationPresenter.Login(userSession.getEmail(), userSession.getPassword());
        }
    }

    @Override
    public void OnLoginFail() {
        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();

    }

    @Override
    public void OnLoginSuccessfully() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void OnEmptyPassword() {
        textInputLayoutPassword.setError(getResources().getString(R.string.emptyPassword));
    }

    @Override
    public void OnEmptyEmail() {
        textInputLayoutEmail.setError(getResources().getString(R.string.emptyEmail));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                authenticationPresenter.Login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                break;

            case R.id.txtRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textInputLayoutPassword.setError(null);
        textInputLayoutEmail.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onBackPressed() {
    }

}
