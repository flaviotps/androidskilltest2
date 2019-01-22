package com.flaviotps.cinq.interfaces;

public interface IAuthenticationPresenter {


    interface Presenter {
        void Login(String email, String password);
    }


    interface View {

        void OnLoginFail();

        void OnLoginSuccessfully();

        void OnEmptyPassword();

        void OnEmptyEmail();

    }
}
