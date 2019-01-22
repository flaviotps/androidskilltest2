package com.flaviotps.cinq.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {

    private static final String database = "database_test";
    private static final int version = 1;
    private static DatabaseConnection databaseConnection;

    public DatabaseConnection(Context context) {
        super(context, database, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("create table users(id integer primary key autoincrement,");
        sql.append("name varchar(50),");
        sql.append("email varchar(50),");
        sql.append("password varchar(50))");

        db.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
