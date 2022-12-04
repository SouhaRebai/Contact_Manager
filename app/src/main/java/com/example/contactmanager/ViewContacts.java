package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

//assignment :  add a search filter
public class ViewContacts extends AppCompatActivity {

    private ListView lv_contacts;
    private SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        lv_contacts = findViewById(R.id.lv_contacts);
        search_view = findViewById(R.id.search_view);
        ContactManager cm = new ContactManager(ViewContacts.this);
        cm.open("contactsBase.db");
        ContactAdapter ad = new ContactAdapter(ViewContacts.this, cm.getAllContacts());
        lv_contacts.setAdapter(ad);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<Contact> filter_contacts = new ArrayList<Contact>();
                for(Contact c : cm.getAllContacts()){
                    if(c.getFirst_name().toLowerCase().contains(s.toLowerCase()) ||
                            c.getLast_name().toLowerCase().contains(s.toLowerCase()) ||
                            c.getNumber().toLowerCase().contains(s.toLowerCase()))
                        filter_contacts.add(c);
                }

                ContactAdapter search_ad = new ContactAdapter(ViewContacts.this, filter_contacts);
                lv_contacts.setAdapter(search_ad);
                return false;
            }
        });

    }
}