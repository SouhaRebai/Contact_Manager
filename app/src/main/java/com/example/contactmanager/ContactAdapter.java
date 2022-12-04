package com.example.contactmanager;
// card element for each contact's presentation within the list view
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> data;
    Context con;

    public ContactAdapter(Context con, ArrayList<Contact> data) {
        this.data = data;
        this.con = con;
    }

    @Override
    public int getCount() {
        // retourne le nombre de views
        //qu'on veut afficher dans la listView
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public TextView tx_first_name, tx_last_name, tx_phone_number;
    public EditText ed_first_name, ed_last_name, ed_phone_number;

    public ImageButton btn_call, btn_edit, btn_delete;
    public CardView card = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // creation d'un view
        LayoutInflater inf = LayoutInflater.from(con);
        card = (CardView) inf.inflate(R.layout.element_layout, null);
        // recuperation des champs
        tx_first_name = card.findViewById(R.id.txt_first_name);
        tx_last_name = card.findViewById(R.id.txt_last_name);
        tx_phone_number = card.findViewById(R.id.txt_phone_number);
        btn_delete = card.findViewById(R.id.btn_delete);
        btn_edit = card.findViewById(R.id.btn_edit);
        btn_call = card.findViewById(R.id.btn_call);
        //get the instance of the contact we wish to edit
        Contact c = data.get(position);
        tx_first_name.setText(c.getFirst_name());
        tx_last_name.setText(c.getLast_name());
        tx_phone_number.setText(c.getNumber());

        ContactManager cm = new ContactManager(con);
        cm.open("contactsBase.db");

        String id = cm.getID(c.getNumber());
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tx_phone_number.getText()));
                con.startActivity(callIntent);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creation d'un view pour l'EDIT
                LayoutInflater lf2 = LayoutInflater.from(con);
                View edit_view = lf2.inflate(R.layout.element_alert, null);
                ed_first_name = edit_view.findViewById(R.id.ed_first_name);
                ed_last_name = edit_view.findViewById(R.id.ed_last_name);
                ed_phone_number = edit_view.findViewById(R.id.ed_phone_number);
                // load old date that will be edited
                ed_first_name.setText(c.getFirst_name());
                ed_last_name.setText(c.getLast_name());
                ed_phone_number.setText(c.getNumber());
                AlertDialog.Builder alert = new AlertDialog.Builder(con);
                alert.setTitle("Edit Contact");
                // afficher le view de l'EDIT sous forme de bulle d'ALERTE
                alert.setView(edit_view);
                alert.setPositiveButton("Save changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fName = ed_first_name.getText().toString();
                        String lName = ed_last_name.getText().toString();
                        String phNumber = ed_phone_number.getText().toString();

                        if (!fName.isEmpty() && !lName.isEmpty() && phNumber.matches("^[24579][0-9]{7}$")) {
                            long verif = cm.edit(id, fName, lName, phNumber);
                            data.set(position, new Contact(id, fName, lName, phNumber));
                            if (verif >= 0){
                            Toast.makeText(con, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            }
                        } else
                            Toast.makeText(con, "Invalid input! Try again", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=alert.create();
                dialog.show();

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(con);
                alert.setTitle("Are you sure you want to delete this contact ?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        ContactManager cm = new ContactManager(con);
                        cm.open("contactsBase.db");
                        cm.delete(id);
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=alert.create();
                dialog.show();

            }
        });
        return card;
    }
}
