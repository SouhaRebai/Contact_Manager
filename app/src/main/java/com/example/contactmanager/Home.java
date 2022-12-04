package com.example.contactmanager;
// after login : welcome to the user + choose add contact or view contacts
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private TextView txt_user;
    private Button btn_view , btn_add ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent x = this.getIntent();
        Bundle b = x.getExtras();
        txt_user= findViewById(R.id.txt_user);
        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        //receive data + implement fonctionnalities
        String username = b.getString("username");
        //received a value from main activity
        txt_user.setText("Welcome back, " + username + "!");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to add a new contact
                Intent addContactCnxt = new Intent(Home.this, AddContact.class);
                addContactCnxt.putExtra("add_trigger","Add a new contact");
                startActivity(addContactCnxt);
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to view existing contacts
                Intent viewContactsCnxt = new Intent(Home.this, ViewContacts.class);
                startActivity(viewContactsCnxt);
            }
        });

    }
}