package com.flaviotps.cinq.interfaces;

import android.content.Context;

import com.flaviotps.cinq.model.UserModel;

public interface IRegistrationPresenter {

    interface Presenter {
        void Register(UserModel user, Context context);

        void Update(UserModel user, Context context);
    }


    interface View {

        void MaxSizeExceeded(String msg);

        void OnRegistrationFail();

        void OnRegistrationSuccessfully();

        void OnUpdateFail();

        void OnUpdateSuccessfully();

        void OnEmptyPassword();

        void OnEmptyEmail();

        void OnEmptyName();

        void OnInvalidEmail();


    }
}
