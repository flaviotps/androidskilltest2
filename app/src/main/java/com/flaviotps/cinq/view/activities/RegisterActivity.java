package com.flaviotps.cinq.view.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.interfaces.IRegistrationPresenter;
import com.flaviotps.cinq.model.UserModel;
import com.flaviotps.cinq.presenter.RegistrationPresenter;

public class RegisterActivity extends AppCompatActivity implements IRegistrationPresenter.View, View.OnClickListener, TextWatcher {

    private RegistrationPresenter registrationPresenter;
    private Button registerButton;
    private TextInputLayout textInputLayoutName, textInputLayoutEmail, textInputLayoutPassword;
    private EditText editTextEmail, editTextPassword, editTextName;
    private UserModel editUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrationPresenter = new RegistrationPresenter(this, this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewBind();

        setEditFields();

    }

    private void setEditFields() {
        if (getIntent().hasExtra("userEdit")) {
            editUser = (UserModel) getIntent().getSerializableExtra("userEdit");
            editTextEmail.setText(editUser.getEmail());
            editTextPassword.setText(editUser.getPassword());
            editTextName.setText(editUser.getName());
            registerButton.setText("Save");

            editTextEmail.setFocusable(false);
            editTextEmail.setFocusableInTouchMode(false);
            editTextEmail.setClickable(false);
            editTextEmail.setBackgroundColor(getResources().getColor(R.color.disabled));
        }
    }

    private void ViewBind() {

        registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(this);

        textInputLayoutEmail = findViewById(R.id.editTextEmailLayout);

        textInputLayoutPassword = findViewById(R.id.editTextPasswordLayout);

        textInputLayoutName = findViewById(R.id.editTextNameLayout);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.addTextChangedListener(this);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.addTextChangedListener(this);

        editTextName = findViewById(R.id.editTextName);
        editTextName.addTextChangedListener(this);


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void MaxSizeExceeded(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnRegistrationFail() {
        Toast.makeText(this, "E-mail already registered", Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnRegistrationSuccessfully() {
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnUpdateFail() {
        Toast.makeText(this, "E-mail already registered", Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnUpdateSuccessfully() {
        Toast.makeText(this, "Edited successfully", Toast.LENGTH_LONG).show();
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
    public void OnEmptyName() {
        textInputLayoutName.setError(getResources().getString(R.string.emptyName));
    }

    @Override
    public void OnInvalidEmail() {
        textInputLayoutEmail.setError(getResources().getString(R.string.invalidEmail));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegister:

                UserModel user = new UserModel();
                user.setName(editTextName.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());

                if (editUser != null) {
                    user.setId(editUser.getId());
                    registrationPresenter.Update(user, this);
                } else {
                    registrationPresenter.Register(user, this);
                }

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
        textInputLayoutName.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
