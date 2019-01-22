package com.flaviotps.cinq.presenter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.flaviotps.cinq.interfaces.IRegistrationPresenter;
import com.flaviotps.cinq.model.UserDAO;
import com.flaviotps.cinq.model.UserModel;

public class RegistrationPresenter implements IRegistrationPresenter.Presenter {

    private static final int MAX_LENGTh = 50;
    private IRegistrationPresenter.View view;
    private Context context;
    private UserDAO userDAO;

    public RegistrationPresenter(IRegistrationPresenter.View view, Context context) {
        this.view = view;
        this.context = context;
        this.userDAO = new UserDAO(context);

    }


    private boolean IsValid(String name, String email, String password) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            if (email.isEmpty()) {
                view.OnEmptyEmail();
            }
            if (password.isEmpty()) {
                view.OnEmptyPassword();
            }
            if (name.isEmpty()) {
                view.OnEmptyName();
            }

            return false;

        } else {

            if (email.length() > MAX_LENGTh) {
                view.MaxSizeExceeded("Email max length is 50");
                return false;
            }

            if (password.length() > MAX_LENGTh) {
                view.MaxSizeExceeded("password max length is 50");
                return false;
            }

            if (name.length() > MAX_LENGTh) {
                view.MaxSizeExceeded("Name max length is 50");
                return false;
            }


            boolean validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

            if (validEmail) {
                return true;
            } else {
                view.OnInvalidEmail();
                return false;
            }
        }
    }

    @Override
    public void Register(UserModel user, Context context) {

        if (IsValid(user.getName(), user.getEmail(), user.getPassword())) {
            long id = userDAO.save(user);
            if (id > 0) {
                view.OnRegistrationSuccessfully();
                ((AppCompatActivity) context).finish();
            } else {
                view.OnRegistrationFail();
            }
        }
    }

    @Override
    public void Update(UserModel user, Context context) {

        if (IsValid(user.getName(), user.getEmail(), user.getPassword())) {
            long id = userDAO.update(user);
            if (id > 0) {
                view.OnUpdateSuccessfully();
                ((AppCompatActivity) context).finish();
            } else {
                view.OnUpdateFail();
            }
        }

    }
}