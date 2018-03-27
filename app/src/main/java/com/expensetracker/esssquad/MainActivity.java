package com.expensetracker.esssquad;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button cr = (Button)this.findViewById(R.id.btn_main_cr);
        Button db = (Button)this.findViewById(R.id.btn_main_db);
        Button sh = (Button)this.findViewById(R.id.btn_main_rep);
        Button bal = (Button)this.findViewById(R.id.btn_main_balance);

        cr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Credit.class);
                startActivity(in);
                finish();
            }
        });
        sh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Report.class);
                startActivity(in);
                finish();
            }
        });
        db.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Debit.class);
                startActivity(in);
                finish();
            }
        });
        bal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Bal_Report.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
