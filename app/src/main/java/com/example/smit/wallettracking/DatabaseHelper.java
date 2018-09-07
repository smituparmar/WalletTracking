package com.example.smit.wallettracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Wallet.db";
    public static final String TABLE_NAME = "SbiPurches";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "POS";
    public static final String COL_3 = "RS";
    public static final String COL_4 = "NUM";
    public static final String COL_5 = "COMPANY";
    public static final String COL_6 = "DATE";
    public static final String COL_7 = "REF";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, POS TEXT, RS TEXT, NUM TEXT, COMPANY TEXT, DATE TEXT, REF TEXT)");
        //db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, POS TEXT, RS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String pos, Double rs, String tex, String company, Date date, String upi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pos);
        contentValues.put(COL_3,rs);
        contentValues.put(COL_4,tex);
        contentValues.put(COL_5,company);
        contentValues.put(COL_6, String.valueOf(date));
        contentValues.put(COL_7,upi);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
