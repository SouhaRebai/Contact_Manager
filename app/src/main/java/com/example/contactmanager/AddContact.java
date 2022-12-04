package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.UUID;

public class AddContact extends AppCompatActivity {
    public ContactManager cm = new ContactManager(AddContact.this);
    private TextView txt_description ;
    String uniqueID ; // each time a new contact is added
    String firstName = null , lastName = null , phoneNumber = null;
    EditText first_name , last_name , phone_number ;
    Button btn_save , btn_discard;
    Intent x ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        first_name = findViewById(R.id.edit_first_name);
        last_name = findViewById(R.id.edit_last_name);
        btn_save = findViewById(R.id.btn_save);
        btn_discard = findViewById(R.id.btn_discard);
        phone_number = findViewById(R.id.edit_phone_number);
        btn_save.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    long a = 0;
                    firstName = first_name.getText().toString();
                    lastName = last_name.getText().toString();
                    phoneNumber = phone_number.getText().toString();
                    if(firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()){
                        Toast.makeText(AddContact.this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        cm.open("contactsBase.db");
                        uniqueID = UUID.randomUUID().toString();
                        a= cm.add(uniqueID,firstName, lastName, phoneNumber);
                        if (a !=  0){
                        Toast.makeText(AddContact.this, "Added successfully to your contacts",
                                Toast.LENGTH_LONG).show();

                    }else{
                            Toast.makeText(AddContact.this, "An error occured! Try again",
                                    Toast.LENGTH_LONG).show();
                        }
                        }
                    first_name.setText("");
                    last_name.setText("");
                    phone_number.setText("");

                       }
            });
        }

    }
