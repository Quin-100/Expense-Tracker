package com.expensetracker.esssquad;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ViewDB extends AppCompatActivity {

    String cat;
    String s2;
    String s3;
    int date;
    int month;
    int year;
    int amount;
    Database db = new Database(this);
    TextView tv_top;

    public ViewDB() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_db);

        getSupportActionBar().setTitle("View Report");

        this.tv_top = (TextView)this.findViewById(R.id.tv_cat);
        Bundle b = this.getIntent().getExtras();
        ArrayList<String> data = b.getStringArrayList("abc");
        ListView lv = (ListView)this.findViewById(R.id.listView1);
        lv.setAdapter(new ArrayAdapter(this,R.layout.listitem,data));//17367043
        this.registerForContextMenu(lv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(this.getApplicationContext(), Report.class);
        this.startActivity(in);
        this.finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.view_db, menu);
        AdapterView.AdapterContextMenuInfo ad = (AdapterView.AdapterContextMenuInfo)menuInfo;
        String selectedWord = ((TextView)ad.targetView).getText().toString();
        StringTokenizer st = new StringTokenizer(selectedWord, " ");
        this.cat = st.nextElement().toString();
        this.s2 = st.nextElement().toString();
        this.s3 = st.nextElement().toString();
        StringTokenizer st2 = new StringTokenizer(this.s2, "₹");
        this.amount = Integer.parseInt(st2.nextElement().toString());
        StringTokenizer st1 = new StringTokenizer(this.s3, "/");
        this.date = Integer.parseInt(st1.nextElement().toString());
        this.month = Integer.parseInt(st1.nextElement().toString());
        this.year = Integer.parseInt(st1.nextElement().toString());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent in;
        switch(item.getItemId()) {
            case R.id.delete:
                this.db.open();
                this.db.deleteEntry(this.amount, this.cat, this.date, this.month, this.year);
                this.db.deleteEntry_deb(this.amount, this.cat, this.date, this.month, this.year);
                in = new Intent(this.getApplicationContext(), Report.class);
                this.startActivity(in);
                this.db.close();
                Toast.makeText(this.getApplicationContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                this.finish();
                return true;
            case R.id.modify:
                this.db.open();
                this.db.deleteEntry(this.amount, this.cat, this.date, this.month, this.year);
                this.db.deleteEntry_deb(this.amount, this.cat, this.date, this.month, this.year);
                this.db.close();
                if(!this.cat.equals("Food") && !this.cat.equals("Rent") && !this.cat.equals("Travel") && !this.cat.equals("Hangout") && !this.cat.equals("Medical") && !this.cat.equals("Grocery") && !this.cat.equals("Mobile") && !this.cat.equals("Cloth") && !this.cat.equals("Petrol") && !this.cat.equals("Misc.")) {
                    in = new Intent(this.getApplicationContext(), Debit.class);
                    this.startActivity(in);
                } else {
                    in = new Intent(this.getApplicationContext(), Credit.class);
                    this.startActivity(in);
                }

                this.finish();
                return true;
            default:
                return false;
        }
    }
}
