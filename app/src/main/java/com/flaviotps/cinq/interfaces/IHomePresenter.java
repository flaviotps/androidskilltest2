package com.flaviotps.cinq.interfaces;

import com.flaviotps.cinq.model.UserModel;

import java.util.List;

public interface IHomePresenter {

    interface Presenter {
        void AddUser();

        void EditUser(UserModel user);

        void RemoveUser(UserModel user);

        void LoadUsers();

        void FilterUsers(String filter);
    }


    interface View {
        void ShowUsers(List<UserModel> users);

        void OnUserDeleted();
    }
}
