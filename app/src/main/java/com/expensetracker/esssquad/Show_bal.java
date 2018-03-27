package com.expensetracker.esssquad;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Show_bal extends AppCompatActivity {

    int inc;
    int exp;
    int bal;
    EditText income;
    EditText expen;
    EditText balance;
    String s_in;
    String s_ex;
    String s_bl;
    TextView tv_balsh_in;
    TextView tv_balsh_ex;
    TextView tv_balsh_cb;

    public Show_bal() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bal);

        getSupportActionBar().setTitle("Show Balance");

        this.income = (EditText)this.findViewById(R.id.et_bal_inc);
        this.income.setKeyListener((KeyListener)null);
        this.expen = (EditText)this.findViewById(R.id.et_bal_ex);
        this.expen.setKeyListener((KeyListener)null);
        this.balance = (EditText)this.findViewById(R.id.et_bal);
        this.balance.setKeyListener((KeyListener)null);
        this.tv_balsh_cb = (TextView)this.findViewById(R.id.tv_yr);
        this.tv_balsh_ex = (TextView)this.findViewById(R.id.tv_day);
        this.tv_balsh_in = (TextView)this.findViewById(R.id.tv_src);

        Bundle b = this.getIntent().getExtras();
        this.inc = b.getInt("inc");
        this.exp = b.getInt("ex");
        System.out.println(this.inc);
        this.s_in = String.valueOf(this.inc);
        this.s_ex = String.valueOf(this.exp);
        this.bal = this.inc - this.exp;
        this.s_bl = String.valueOf(this.bal);
        this.income.setText(this.s_in);
        this.expen.setText(this.s_ex);
        this.balance.setText(this.s_bl);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this.getApplicationContext(), Bal_Report.class);
        this.startActivity(in);
        this.finish();
    }
}
