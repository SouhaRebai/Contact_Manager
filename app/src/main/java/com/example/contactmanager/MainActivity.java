package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edit_name , edit_pass;
    TextView txt_signup ;
    private Button btn_confirm , btn_cancel;
    String name , password ;
    Boolean verif = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.edit_name1);
        edit_pass= findViewById(R.id.edit_pass1);
        txt_signup = findViewById(R.id.txt_signup);
        btn_confirm = findViewById(R.id.btn_create1);
        btn_cancel = findViewById(R.id.btn_cancel1);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager um = new UserManager(MainActivity.this);
                um.open();
                name = edit_name.getText().toString();
                password = edit_pass.getText().toString();
                verif = um.checkCredentials(name, password);
                if(verif) {
                    Intent i = new Intent(MainActivity.this, Home.class);
                    i.putExtra("username", name);
                    startActivity(i);
                }else {
                    Toast.makeText(MainActivity.this, "Username or password does not exit. Please try again!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.this.finish();
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);            }
        });
    }
}