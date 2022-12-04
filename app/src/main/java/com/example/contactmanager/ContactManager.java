package com.example.contactmanager;
// CRUD operations + useful functions to get certain database elements
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db = null;
    Context con ;
    ContactManager(Context con){
        this.con = con ;
    }
    //////////////////////////////////////////////// START CONNECTION //////////////////////////////////////////:
    public void open(String database){
        // start a connection with local database
    // si on modifie la version ==> appel implicite à onUpgrade
    ContactHelper helper = new ContactHelper(con,database,null,1);
    db = helper.getWritableDatabase();
    }
    /////////////////////////////////////////////////END CONNECTION ::::::::::::::::::::::::::::::::::::::::
    public void close(){
        // terminate the database connection
        db.close();
    }
    /////////////////////////////////////////// add a new contact to the list////////////////////////////
public long add(String id, String first_name , String last_name , String phone_number){
    long a = 0 ;
    ContentValues values = new ContentValues();
    values.put(ContactHelper.col_id,id);
    values.put(ContactHelper.col_first_name,first_name);
    values.put(ContactHelper.col_last_name,last_name);
    values.put(ContactHelper.col_phone_number,phone_number);
    a = db.insert(ContactHelper.table_contacts, null,values);
    return a;
}
///////////////////////////////////////////////////edit an existing contact////////////////////////////////////
    public long edit(String id , String first_name , String last_name , String phone_number){
        long a = -1 ;
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_id,id);
        values.put(ContactHelper.col_first_name,first_name);
        values.put(ContactHelper.col_last_name,last_name);
        values.put(ContactHelper.col_phone_number,phone_number);
        a = db.update(ContactHelper.table_contacts,
                values, ContactHelper.col_id+"='"+id+"'" ,null);
        if (a>= 0)
            System.out.println("User modified");
        return a;
    }
    /////////////////////////////////////////////////delete contact /////////////////////////////////////////////
    long delete(String id)
    {
        int a=-1;
        a=db.delete(ContactHelper.table_contacts,
                ContactHelper.col_id+"='"+id+"'",null);
        return a;
    }
    ////////////////////////// a get ID function is necessary for later edits/////////////////////////////////
    String getID(String phone){
        Cursor cr = db.query(ContactHelper.table_contacts, new String[]{ContactHelper.col_id}, ContactHelper.col_phone_number +"='"+ phone+"'", null, null, null, null);
        cr.moveToFirst();
        return cr.getString(0);
    }
///////////////////////////////////////render the list of contacts///////////////////////////////////////
public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts_list = new ArrayList<Contact>();
        Cursor cr = db.query(ContactHelper.table_contacts,
                new String[] { ContactHelper.col_id,
                        ContactHelper.col_first_name,
                        ContactHelper.col_last_name,
                        ContactHelper.col_phone_number,
                },null,null,null,null, null);
        cr.moveToFirst();
        while(!cr.isAfterLast()) {
        String i1 = cr.getString(0);
        String i2 = cr.getString(1);
        String i3 = cr.getString(2);
        String i4 = cr.getString(3);
        contacts_list.add(new Contact(i1, i2, i3, i4));
        cr.moveToNext();
    }
return contacts_list;
}


}
