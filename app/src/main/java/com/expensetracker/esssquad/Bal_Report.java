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
import android.widget.Spinner;
import android.widget.TextView;

public class Bal_Report extends AppCompatActivity {

    Button mth_bal;
    Button yr_bal;
    Spinner sp_mth_bal;
    Spinner sp_yr_bal;
    int br_mth;
    int br_yr;
    int total_ex;
    int total_in;
    Database info;
    Intent in;
    Bundle b;
    Bundle b1;
    TextView tv_balrp_mth;
    TextView tv_balrp_yr;
    TextView tv_balrp;

    public Bal_Report() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bal_report);

        getSupportActionBar().setTitle("Balance Report");

        this.tv_balrp_mth = (TextView)this.findViewById(R.id.tv_balrp_mth);
        this.tv_balrp_yr = (TextView)this.findViewById(R.id.tv_balrp_yr);
        this.tv_balrp = (TextView)this.findViewById(R.id.tv_balrp);
        this.mth_bal = (Button)this.findViewById(R.id.btn_mth_bal);
        this.yr_bal = (Button)this.findViewById(R.id.btn_yr_bal);
        this.sp_mth_bal = (Spinner)this.findViewById(R.id.sp_mth_balrp);
        this.sp_yr_bal = (Spinner)this.findViewById(R.id.sp_yr_balrp);

        this.info = new Database(this);

        this.mth_bal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bal_Report.this.br_mth = Integer.parseInt(Bal_Report.this.sp_mth_bal.getSelectedItem().toString());
                Bal_Report.this.br_yr = Integer.parseInt(Bal_Report.this.sp_yr_bal.getSelectedItem().toString());
                Bal_Report.this.info.open();
                Bal_Report.this.total_ex = Bal_Report.this.info.getsum(Bal_Report.this.br_mth, Bal_Report.this.br_yr);
                Bal_Report.this.total_in = Bal_Report.this.info.getsum_in(Bal_Report.this.br_mth, Bal_Report.this.br_yr);
                Bal_Report.this.in = new Intent(Bal_Report.this.getApplicationContext(), Show_bal.class);
                Bal_Report.this.b = new Bundle();
                Bal_Report.this.b1 = new Bundle();
                Bal_Report.this.b.putInt("ex", Bal_Report.this.total_ex);
                Bal_Report.this.b1.putInt("inc", Bal_Report.this.total_in);
                Bal_Report.this.in.putExtras(Bal_Report.this.b);
                Bal_Report.this.in.putExtras(Bal_Report.this.b1);
                Bal_Report.this.startActivityForResult(Bal_Report.this.in, 1);
                Bal_Report.this.finish();
            }
        });
        this.yr_bal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bal_Report.this.br_yr = Integer.parseInt(Bal_Report.this.sp_yr_bal.getSelectedItem().toString());
                Bal_Report.this.info.open();
                Bal_Report.this.total_ex = Bal_Report.this.info.getsum(Bal_Report.this.br_yr);
                Bal_Report.this.total_in = Bal_Report.this.info.getsum_in(Bal_Report.this.br_yr);
                Bal_Report.this.in = new Intent(Bal_Report.this.getApplicationContext(), Show_bal.class);
                Bal_Report.this.b = new Bundle();
                Bal_Report.this.b1 = new Bundle();
                Bal_Report.this.b.putInt("ex", Bal_Report.this.total_ex);
                Bal_Report.this.b1.putInt("inc", Bal_Report.this.total_in);
                Bal_Report.this.in.putExtras(Bal_Report.this.b);
                Bal_Report.this.in.putExtras(Bal_Report.this.b1);
                Bal_Report.this.startActivityForResult(Bal_Report.this.in, 1);
                Bal_Report.this.finish();
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
