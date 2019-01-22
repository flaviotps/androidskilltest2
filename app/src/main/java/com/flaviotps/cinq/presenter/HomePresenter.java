package com.flaviotps.cinq.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.interfaces.IHomePresenter;
import com.flaviotps.cinq.model.UserDAO;
import com.flaviotps.cinq.model.UserModel;
import com.flaviotps.cinq.view.activities.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements IHomePresenter.Presenter {


    private IHomePresenter.View view;
    private Context context;
    private UserDAO userDAO;

    public HomePresenter(IHomePresenter.View view, Context context) {
        this.view = view;
        this.context = context;
        this.userDAO = new UserDAO(context);
    }

    @Override
    public void AddUser() {

    }

    @Override
    public void EditUser(UserModel user) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("userEdit", user);
        context.startActivity(intent);
    }

    @Override
    public void RemoveUser(final UserModel user) {

        if (user.getId() == UserModel.getCurrentUser().getId()) {

            final Dialog dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_warning);
            Button dialogButtonOK = dialog.findViewById(R.id.btn_dialog_ok);
            dialogButtonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        } else {

            final Dialog dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_delete);
            Button dialogButtonOK = dialog.findViewById(R.id.btn_dialog_ok);
            dialogButtonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userDAO.delete(user) > 0) {
                        LoadUsers();
                        view.OnUserDeleted();
                        dialog.dismiss();
                    }
                }
            });

            Button dialogButtonCancel = dialog.findViewById(R.id.btn_dialog_cancel);
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }


    public void DestroySession() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void LoadUsers() {
        view.ShowUsers(userDAO.getAllUsers());
    }

    @Override
    public void FilterUsers(String filter) {

        if (filter.isEmpty() || filter.trim().length() == 0) {
            view.ShowUsers(UserModel.getUsersList());
        }

        List<UserModel> userList = new ArrayList<>();
        for (UserModel user : UserModel.getUsersList()) {
            if (user.getName().toLowerCase().contains(filter.toLowerCase())) {
                userList.add(user);
            }
        }

        view.ShowUsers(userList);
    }
}
