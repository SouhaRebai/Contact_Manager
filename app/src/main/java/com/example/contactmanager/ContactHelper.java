package com.example.contactmanager;
// class that ensures database connection and update in case of versionnig
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final String table_contacts="Contacts";
    public static final String col_id = "id";
    public static final String col_first_name ="first_name";
    public static final String col_last_name = "last_name";
    public static final String col_phone_number="phone_number";
    String query = "create table " + table_contacts + "( "
                +col_id + " Text Primary Key , "
                +col_first_name +" Text not null , "
                +col_last_name +" Text not null , "
                +col_phone_number +" Text not null )";

    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
        db.execSQL("drop table "+ table_contacts);
        onCreate(db);

    }
}
