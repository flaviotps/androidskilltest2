package com.flaviotps.cinq.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.flaviotps.cinq.interfaces.IAuthenticationPresenter;
import com.flaviotps.cinq.model.UserDAO;
import com.flaviotps.cinq.model.UserModel;

public class AuthenticationPresenter implements IAuthenticationPresenter.Presenter {

    private IAuthenticationPresenter.View view;
    private Context context;
    private UserDAO userDAO;

    public AuthenticationPresenter(IAuthenticationPresenter.View view, Context context) {
        this.view = view;
        this.context = context;
        this.userDAO = new UserDAO(context);
    }


    private boolean IsValid(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            if (email.isEmpty()) {
                view.OnEmptyEmail();
            }
            if (password.isEmpty()) {
                view.OnEmptyPassword();
            }

            return false;
        } else {

            return true;
        }

    }

    public UserModel getLocalSession() {
        UserModel user = new UserModel();
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        user.setId(preferences.getInt("id", 0));
        user.setName(preferences.getString("name", null));
        user.setEmail(preferences.getString("email", null));
        user.setPassword(preferences.getString("password", null));
        return user;
    }

    public void setLocalSession(UserModel user) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.commit();
    }

    @Override
    public void Login(String email, String password) {
        if (IsValid(email, password)) {

            UserModel user = userDAO.getUser(email, password);

            if (user == null) {
                view.OnLoginFail();
            } else {
                setLocalSession(user);
                view.OnLoginSuccessfully();
            }
        }
    }
}
