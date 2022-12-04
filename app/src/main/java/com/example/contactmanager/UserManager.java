package com.example.contactmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserManager  {
    SQLiteDatabase db = null;
    Context con ;
    UserManager(Context con){
        this.con = con ;
    }
    public void open(){
        UserHelper helper = new UserHelper(con,"usersBase.db",null,1);
        db = helper.getWritableDatabase();
    }
    public long add(String id , String email , String username , String password){
        long a = 0 ;
        ContentValues values = new ContentValues();
        values.put(UserHelper.col_id,id);
        values.put(UserHelper.col_email,email);
        values.put(UserHelper.col_username,username);
        values.put(UserHelper.col_password,password);
        a = db.insert(UserHelper.table_users, null,values);
        return a;
    }
    public long getOneUser(String id , String email , String username , String password){
        long a = 0 ;
        ContentValues values = new ContentValues();
        values.put(UserHelper.col_username,username);
        values.put(UserHelper.col_password,password);
        a = db.insert(UserHelper.table_users, null,values);
        return a;
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users_list = new ArrayList<User>();
        Cursor cr = db.query(ContactHelper.table_contacts,
                new String[] { UserHelper.col_id,
                        UserHelper.col_email,
                        UserHelper.col_username,
                        UserHelper.col_password,
                },null,null,null,null, null);
        cr.moveToFirst();
        while(!cr.isAfterLast()) {
            String i1 = cr.getString(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            String i4 = cr.getString(3);
            users_list.add(new User(i1, i2, i3, i4));
            cr.moveToNext();
        }
        return users_list;
    }
    public Boolean checkUsername(String username){
            Cursor cursor = db.query(UserHelper.table_users, new String[]{UserHelper.col_id}, UserHelper.col_username +"='"+ username+"'", null, null, null, null);
            if(cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean checkEmail(String email){

        Cursor cursor = db.rawQuery("Select * from "+ UserHelper.table_users +" where "+ UserHelper.col_email +" = ?",new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean checkCredentials(String username , String password){
        Cursor cursor = db.rawQuery("Select * from "+ UserHelper.table_users +" where "+ UserHelper.col_username +" = ? and "+UserHelper.col_password +" = ?",new String[]{username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }



}
