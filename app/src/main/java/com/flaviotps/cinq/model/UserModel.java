package com.flaviotps.cinq.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements Serializable {

    private static UserModel currentUser = null;
    private static List<UserModel> usersList = new ArrayList<>();

    private int id;
    private String name;
    private String email;
    private String password;

    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserModel currentUser) {
        UserModel.currentUser = currentUser;
    }

    public static List<UserModel> getUsersList() {
        return usersList;
    }

    public static void setUsersList(List<UserModel> usersList) {
        UserModel.usersList = usersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
