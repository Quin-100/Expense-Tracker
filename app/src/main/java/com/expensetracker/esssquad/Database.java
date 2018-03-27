package com.expensetracker.esssquad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by M.Qasim on 23-03-2018.
 */

public class Database {

    public static final String KEY_C_ROWID = "_id";
    public static final String KEY_C_TYPE = "c_type";
    public static final String KEY_C_AMOUNT = "c_amount";
    public static final String KEY_C_DAY = "c_day";
    public static final String KEY_C_MONTH = "c_month";
    public static final String KEY_C_YEAR = "c_year";
    private static final String DB_NAME = "expense_manager";
    private static final String DB_TABLE = "credit_table";
    private static final String DB_TABLE_DEB = "debit_table";
    private static final int DB_VERSION = 1;
    private Database.DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public Database(Context c) {
        this.ourContext = c;
    }

    public Database open() {
        this.ourHelper = new Database.DbHelper(this.ourContext);
        this.ourDatabase = this.ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.ourHelper.close();
    }

    public long createEntry(String type, int amount, int day, int month, int year) {
        ContentValues cv = new ContentValues();
        cv.put("c_type", type);
        cv.put("c_amount", Integer.valueOf(amount));
        cv.put("c_day", Integer.valueOf(day));
        cv.put("c_month", Integer.valueOf(month));
        cv.put("c_year", Integer.valueOf(year));
        return this.ourDatabase.insert("credit_table", (String)null, cv);
    }

    public ArrayList<String> getData(int r_mth, int r_yr) {
        String[] columns = new String[]{"c_type", "c_amount", "c_day", "c_month", "c_year"};
        Cursor c = this.ourDatabase.query("credit_table", columns, "c_month = " + r_mth + " and " + "c_year" + " = " + r_yr, (String[])null, (String)null, (String)null, (String)null);
        ArrayList<String> res = new ArrayList();
        int iType = c.getColumnIndex("c_type");
        int iAmount = c.getColumnIndex("c_amount");
        int iDay = c.getColumnIndex("c_day");
        int iMonth = c.getColumnIndex("c_month");
        int iYear = c.getColumnIndex("c_year");
        c.moveToFirst();

        while(!c.isAfterLast()) {
            res.add(c.getString(iType) + "              " + "₹" + c.getString(iAmount) + "           " + c.getString(iDay) + "/" + c.getString(iMonth) + "/" + c.getString(iYear));
            c.moveToNext();
        }

        return res;
    }

    public void deleteEntry(int amount, String cat, int date, int month, int year) {
        this.ourDatabase.delete("credit_table", "c_type='" + cat + "' and " + "c_year" + " = " + year + " and " + "c_month" + " = " + month + " and " + "c_day" + " = " + date + " and " + "c_amount" + " = " + amount, (String[])null);
    }

    public int getsum(int br_mth, int br_yr) {
        int total = 0;
        Cursor cursor = this.ourDatabase.rawQuery("SELECT SUM(c_amount) FROM credit_table WHERE c_month = " + br_mth + " and c_year = " + br_yr, (String[])null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public long deb_createEntry(String type, int amount, int day, int month, int year) {
        ContentValues cv = new ContentValues();
        cv.put("c_type", type);
        cv.put("c_amount", Integer.valueOf(amount));
        cv.put("c_day", Integer.valueOf(day));
        cv.put("c_month", Integer.valueOf(month));
        cv.put("c_year", Integer.valueOf(year));
        return this.ourDatabase.insert("debit_table", (String)null, cv);
    }

    public ArrayList<String> getData_in_mth(int r_mth, int r_yr) {
        String[] columns = new String[]{"c_type", "c_amount", "c_day", "c_month", "c_year"};
        Cursor c = this.ourDatabase.query("debit_table", columns, "c_month = " + r_mth + " and " + "c_year" + " = " + r_yr, (String[])null, (String)null, (String)null, (String)null);
        ArrayList<String> res = new ArrayList();
        int iType = c.getColumnIndex("c_type");
        int iAmount = c.getColumnIndex("c_amount");
        int iDay = c.getColumnIndex("c_day");
        int iMonth = c.getColumnIndex("c_month");
        int iYear = c.getColumnIndex("c_year");
        c.moveToFirst();

        while(!c.isAfterLast()) {
            res.add(c.getString(iType) + "              " + "₹" + c.getString(iAmount) + "           " + c.getString(iDay) + "/" + c.getString(iMonth) + "/" + c.getString(iYear));
            c.moveToNext();
        }

        return res;
    }

    public ArrayList<String> getData_in_yr(int r_yr) {
        String[] columns = new String[]{"c_type", "c_amount", "c_day", "c_month", "c_year"};
        Cursor c = this.ourDatabase.query("debit_table", columns, "c_year = " + r_yr, (String[])null, (String)null, (String)null, (String)null);
        ArrayList<String> res = new ArrayList();
        int iType = c.getColumnIndex("c_type");
        int iAmount = c.getColumnIndex("c_amount");
        int iDay = c.getColumnIndex("c_day");
        int iMonth = c.getColumnIndex("c_month");
        int iYear = c.getColumnIndex("c_year");
        c.moveToFirst();

        while(!c.isAfterLast()) {
            res.add(c.getString(iType) + "              " + "₹" + c.getString(iAmount) + "           " + c.getString(iDay) + "/" + c.getString(iMonth) + "/" + c.getString(iYear));
            c.moveToNext();
        }

        return res;
    }

    public ArrayList<String> getData_ex_yr(int r_yr) {
        String[] columns = new String[]{"c_type", "c_amount", "c_day", "c_month", "c_year"};
        Cursor c = this.ourDatabase.query("credit_table", columns, "c_year = " + r_yr, (String[])null, (String)null, (String)null, (String)null);
        ArrayList<String> res = new ArrayList();
        int iType = c.getColumnIndex("c_type");
        int iAmount = c.getColumnIndex("c_amount");
        int iDay = c.getColumnIndex("c_day");
        int iMonth = c.getColumnIndex("c_month");
        int iYear = c.getColumnIndex("c_year");
        c.moveToFirst();

        while(!c.isAfterLast()) {
            res.add(c.getString(iType) +  "              " + "₹" + c.getString(iAmount) + "           " + c.getString(iDay) + "/" + c.getString(iMonth) + "/" + c.getString(iYear));
            c.moveToNext();
        }

        return res;
    }

    public int getsum_in(int br_mth, int br_yr) {
        int total = 0;
        Cursor cursor = this.ourDatabase.rawQuery("SELECT SUM(c_amount) FROM debit_table WHERE c_month = " + br_mth + " and c_year = " + br_yr, (String[])null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public int getsum(int br_yr) {
        int total = 0;
        Cursor cursor = this.ourDatabase.rawQuery("SELECT SUM(c_amount) FROM credit_table WHERE c_year = " + br_yr, (String[])null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public int getsum_in(int br_yr) {
        int total = 0;
        Cursor cursor = this.ourDatabase.rawQuery("SELECT SUM(c_amount) FROM debit_table WHERE c_year = " + br_yr, (String[])null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public void deleteEntry_deb(int amount, String cat, int date, int month, int year) {
        this.ourDatabase.delete("debit_table", "c_type='" + cat + "' and " + "c_year" + " = " + year + " and " + "c_month" + " = " + month + " and " + "c_day" + " = " + date + " and " + "c_amount" + " = " + amount, (String[])null);
    }

    private static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, "expense_manager", (SQLiteDatabase.CursorFactory)null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(" CREATE TABLE credit_table ( _id INTEGER PRIMARY KEY , c_type TEXT NOT NULL , c_amount INTEGER NOT NULL , c_day INTEGER NOT NULL , c_month INTEGER NOT NULL , c_year INTEGER NOT NULL ); ");
            db.execSQL(" CREATE TABLE debit_table ( _id INTEGER PRIMARY KEY , c_type TEXT NOT NULL , c_amount INTEGER NOT NULL , c_day INTEGER NOT NULL , c_month INTEGER NOT NULL , c_year INTEGER NOT NULL ); ");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS credit_table");
            db.execSQL("DROP TABLE IF EXISTS debit_table");
            this.onCreate(db);
        }
    }

}
