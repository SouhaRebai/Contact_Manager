package com.example.contactmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserHelper extends SQLiteOpenHelper {
    public static final String table_users="Users";
    public static final String col_id = "id";
    public static final String col_username ="username";
    public static final String col_password = "password";
    public static final String col_email="email";
    String query = "create table " + table_users + "( "
            +col_id + " Text Primary Key , "
            +col_email +" Text not null , "
            +col_username +" Text not null , "
            +col_password +" Text not null )";

    public UserHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //execution lors de l'ouverture de la base pour la premiere fois
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //modification de la version
        db.execSQL("drop table "+ table_users);
        onCreate(db);

    }
}
