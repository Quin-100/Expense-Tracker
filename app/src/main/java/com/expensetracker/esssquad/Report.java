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

import java.util.ArrayList;

public class Report extends AppCompatActivity {

    Button in_month;
    Button in_year;
    Button ex_month;
    Button ex_year;
    Spinner r_month;
    Spinner r_year;
    int r_mth;
    int r_yr;
    TextView rp_tv_month;
    TextView rp_tv_year;
    TextView rp_tv_mthrp;
    TextView rp_tv_yrrp;
    Database info = new Database(this);

    public Report() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        this.rp_tv_month = (TextView)this.findViewById(R.id.rp_tv_month);
        this.rp_tv_year = (TextView)this.findViewById(R.id.rp_tv_year);
        this.rp_tv_mthrp = (TextView)this.findViewById(R.id.rp_tv_mthrp);
        this.rp_tv_yrrp = (TextView)this.findViewById(R.id.rp_tv_yrrp);
        this.ex_month = (Button)this.findViewById(R.id.btn_rpm_cr);
        this.in_month = (Button)this.findViewById(R.id.btn_rpm_db);
        this.ex_year = (Button)this.findViewById(R.id.btn_rpy_cr);
        this.in_year = (Button)this.findViewById(R.id.btn_rpy_db);
        this.r_month = (Spinner)this.findViewById(R.id.sp_rp_mth);
        this.r_year = (Spinner)this.findViewById(R.id.sp_rp_yr);

        this.ex_month.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Report.this.r_mth = Integer.parseInt(Report.this.r_month.getSelectedItem().toString());
                Report.this.r_yr = Integer.parseInt(Report.this.r_year.getSelectedItem().toString());
                Report.this.info.open();
                ArrayList<String> data = Report.this.info.getData(Report.this.r_mth, Report.this.r_yr);
                Intent in = new Intent(Report.this.getApplicationContext(), ViewDB.class);
                Bundle b1 = new Bundle();
                b1.putStringArrayList("abc", data);
                in.putExtras(b1);
                Report.this.startActivityForResult(in, 1);
                Report.this.info.close();
                Report.this.finish();
            }
        });
        this.in_month.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Report.this.r_mth = Integer.parseInt(Report.this.r_month.getSelectedItem().toString());
                Report.this.r_yr = Integer.parseInt(Report.this.r_year.getSelectedItem().toString());
                Report.this.info.open();
                ArrayList<String> data = Report.this.info.getData_in_mth(Report.this.r_mth, Report.this.r_yr);
                Intent in = new Intent(Report.this.getApplicationContext(), ViewDB.class);
                Bundle b1 = new Bundle();
                b1.putStringArrayList("abc", data);
                in.putExtras(b1);
                Report.this.startActivityForResult(in, 1);
                Report.this.info.close();
                Report.this.finish();
            }
        });
        this.in_year.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Report.this.r_yr = Integer.parseInt(Report.this.r_year.getSelectedItem().toString());
                Report.this.info.open();
                ArrayList<String> data = Report.this.info.getData_in_yr(Report.this.r_yr);
                Intent in = new Intent(Report.this.getApplicationContext(), ViewDB.class);
                Bundle b1 = new Bundle();
                b1.putStringArrayList("abc", data);
                in.putExtras(b1);
                Report.this.startActivityForResult(in, 1);
                Report.this.info.close();
                Report.this.finish();
            }
        });
        this.ex_year.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Report.this.r_yr = Integer.parseInt(Report.this.r_year.getSelectedItem().toString());
                Report.this.info.open();
                ArrayList<String> data = Report.this.info.getData_ex_yr(Report.this.r_yr);
                Intent in = new Intent(Report.this.getApplicationContext(), ViewDB.class);
                Bundle b1 = new Bundle();
                b1.putStringArrayList("abc", data);
                in.putExtras(b1);
                Report.this.startActivityForResult(in, 1);
                Report.this.info.close();
                Report.this.finish();
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
