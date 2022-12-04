package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.UUID;

public class SignUp extends AppCompatActivity {
    EditText ed_mail , ed_username , ed_password, ed_verif_password ;
    Button btn_create , btn_cancel;
    Boolean verif_email_1 = true , verif_email_2 = true , verif_pass = true, verif_username = true  ;
    UserManager um ;
    String uniqueID ;
    long a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ed_mail = findViewById(R.id.edit_mail);
        ed_username = findViewById(R.id.edit_name1);
        ed_password = findViewById(R.id.edit_pass1);
        ed_verif_password = findViewById(R.id.edit_pass2);
        btn_create = findViewById(R.id.btn_create1);
        btn_cancel = findViewById(R.id.btn_cancel1);
        um = new UserManager(SignUp.this);
        um.open();
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // --- email verification ---
                verif_email_1 = !TextUtils.isEmpty(ed_mail.getText().toString())
                        && Patterns.EMAIL_ADDRESS.matcher(ed_mail.getText().toString()).matches();
                verif_email_2 = um.checkEmail(ed_mail.getText().toString());
                // --- password verification ---
                verif_pass = ed_password.getText().toString().equals(ed_verif_password.getText().toString());
                //username existence verification
                verif_username = um.checkUsername(ed_username.getText().toString()) ;
                // --- dodge null values exceptions ---
                if (ed_mail.getText().toString().equals("") ||
                        ed_username.getText().toString().equals("") ||
                        ed_password.getText().toString().equals("")||
                        ed_verif_password.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "PLease fill in all the fields!",
                            Toast.LENGTH_LONG).show();
                }
                else if (!verif_email_1){
                    Toast.makeText(SignUp.this, "Email format is incorrect. Try again!",
                            Toast.LENGTH_LONG).show();
                }
                else if(!verif_email_2){
                        Toast.makeText(SignUp.this, "Email already exists!",
                                Toast.LENGTH_LONG).show();
                }
                else if(!verif_username){
                    Toast.makeText(SignUp.this, "Username already exists!",
                            Toast.LENGTH_LONG).show();
                }
                else if(!verif_pass){
                    Toast.makeText(SignUp.this, "Passwords do not match. Check your typing!",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    uniqueID = UUID.randomUUID().toString();
                    long a = um.add(uniqueID, ed_mail.getText().toString(), ed_username.getText().toString(),ed_password.getText().toString());
                    if (a>0){
                        ed_mail.setText("");
                        ed_username.setText("");
                        ed_password.setText("");
                        ed_verif_password.setText("");
                        Toast.makeText(SignUp.this, "New user added succesfully",
                                Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SignUp.this, "Error encountered. Try again!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}