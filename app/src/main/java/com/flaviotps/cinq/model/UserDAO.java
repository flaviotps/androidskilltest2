package com.flaviotps.cinq.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private DatabaseConnection databaseConnection;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        databaseConnection = new DatabaseConnection(context);
        sqLiteDatabase = databaseConnection.getWritableDatabase();
    }

    public int delete(UserModel user) {
        return sqLiteDatabase.delete("users", "id = ?", new String[]{String.valueOf(user.getId())});

    }

    public long save(UserModel user) {

        if (getUserByEmail(user.getEmail()) == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", user.getName());
            contentValues.put("email", user.getEmail());
            contentValues.put("password", user.getPassword());

            return sqLiteDatabase.insert("users", null, contentValues);
        } else {
            return 0;
        }

    }

    public long update(UserModel user) {


        if (getUserByEmail(user.getEmail()) == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", user.getName());
            contentValues.put("email", user.getEmail());
            contentValues.put("password", user.getPassword());

            return sqLiteDatabase.update("users", contentValues, "id = ?", new String[]{String.valueOf(user.getId())});
        } else if (getUserByEmail(user.getEmail()).getId() == user.getId()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", user.getName());
            contentValues.put("email", user.getEmail());
            contentValues.put("password", user.getPassword());
            return sqLiteDatabase.update("users", contentValues, "id = ?", new String[]{String.valueOf(user.getId())});
        } else {

            return 0;
        }

    }

    public List<UserModel> getAllUsers() {

        List<UserModel> userList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query("users", new String[]{"id", "name", "email", "password"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            UserModel user = new UserModel();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            userList.add(user);
        }

        UserModel.setUsersList(userList);

        return userList;
    }


    public UserModel getUser(String email, String password) {

        UserModel user = null;

        Cursor cursor = sqLiteDatabase.query("users", new String[]{"id", "name", "email", "password"}, "email =? AND password =?", new String[]{email, password}, null, null, null);

        while (cursor.moveToNext()) {
            user = new UserModel();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));

            UserModel.setCurrentUser(user);
            return user;
        }

        return user;
    }

    public UserModel getUserByEmail(String email) {

        UserModel user = null;

        Cursor cursor = sqLiteDatabase.query("users", new String[]{"id", "name", "email", "password"}, "email =?", new String[]{email}, null, null, null);

        while (cursor.moveToNext()) {
            user = new UserModel();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            return user;
        }

        return user;
    }
}
