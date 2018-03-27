package com.expensetracker.esssquad;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Credit extends AppCompatActivity {

    TextView cr_tv_cat;
    TextView cr_tv_amt;
    TextView cr_tv_day;
    TextView cr_tv_mth;
    TextView cr_tv_yr;
    String type;
    String ch_amt;
    Database entry = new Database(this);

    public Credit() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        getSupportActionBar().setTitle("Expenditure");

        this.cr_tv_cat = (TextView)this.findViewById(R.id.tv_cat);
        this.cr_tv_amt = (TextView)this.findViewById(R.id.tv_amt);
        this.cr_tv_day = (TextView)this.findViewById(R.id.tv_day);
        this.cr_tv_mth = (TextView)this.findViewById(R.id.tv_month);
        this.cr_tv_yr = (TextView)this.findViewById(R.id.tv_yr);
        final Spinner s_type = (Spinner)this.findViewById(R.id.cr_sp_type);
        final Spinner s_day = (Spinner)this.findViewById(R.id.sp_day);
        final Spinner s_month = (Spinner)this.findViewById(R.id.sp_month);
        final Spinner s_year = (Spinner)this.findViewById(R.id.sp_year);
        Button b = (Button)this.findViewById(R.id.btn_sub);
        final EditText amt = (EditText)this.findViewById(R.id.cr_et_amt);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Credit.this.ch_amt = amt.getText().toString();
                if(Credit.this.ch_amt.equals("")) {
                    Toast.makeText(Credit.this.getApplicationContext(), "Please Fill Amount ", Toast.LENGTH_SHORT).show();
                } else {
                    Credit.this.type = s_type.getSelectedItem().toString();
                    int amount = Integer.parseInt(amt.getText().toString());
                    int day = Integer.parseInt(s_day.getSelectedItem().toString());
                    int month = Integer.parseInt(s_month.getSelectedItem().toString());
                    int year = Integer.parseInt(s_year.getSelectedItem().toString());
                    Credit.this.entry.open();
                    Credit.this.entry.createEntry(Credit.this.type, amount, day, month, year);
                    Credit.this.entry.close();
                    Toast.makeText(Credit.this.getApplicationContext(), "DATABASE UPDATED", Toast.LENGTH_SHORT).show();
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
