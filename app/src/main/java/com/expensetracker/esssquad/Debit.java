package com.expensetracker.esssquad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Debit extends AppCompatActivity {

    Database info = new Database(this);
    Button sub;
    Spinner sp_day;
    Spinner sp_month;
    Spinner sp_year;
    EditText db_src;
    EditText db_amt;
    String type;
    String amt;

    public Debit() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        getSupportActionBar().setTitle("Income");

        this.sub = (Button)this.findViewById(R.id.btn_sub);
        this.sp_day = (Spinner)this.findViewById(R.id.sp_deb_day);
        this.sp_month = (Spinner)this.findViewById(R.id.sp_month);
        this.sp_year = (Spinner)this.findViewById(R.id.sp_year);
        this.db_src = (EditText)this.findViewById(R.id.ed_deb_src);
        this.db_amt = (EditText)this.findViewById(R.id.ed_deb_amt);
        this.sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Debit.this.type = Debit.this.db_src.getText().toString();
                Debit.this.amt = Debit.this.db_amt.getText().toString();
                if(!Debit.this.type.equals("") && !Debit.this.amt.equals("")) {
                    int amount = Integer.parseInt(Debit.this.db_amt.getText().toString());
                    int day = Integer.parseInt(Debit.this.sp_day.getSelectedItem().toString());
                    int month = Integer.parseInt(Debit.this.sp_month.getSelectedItem().toString());
                    int year = Integer.parseInt(Debit.this.sp_year.getSelectedItem().toString());
                    Debit.this.info.open();
                    Debit.this.info.deb_createEntry(Debit.this.type, amount, day, month, year);
                    Debit.this.info.close();
                    Toast.makeText(Debit.this.getApplicationContext(), "DATABASE UPDATED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Debit.this.getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this.getApplicationContext(), MainActivity.class);
        this.startActivity(in);
        this.finish();
    }
}
