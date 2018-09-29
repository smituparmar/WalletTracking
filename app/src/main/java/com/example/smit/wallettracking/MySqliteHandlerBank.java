package com.example.smit.wallettracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MySqliteHandlerBank extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="computers.db";
    private static final String  TABLE_BANK="Bank";
    private static final String  TABLE_spend="SPEND";

    private static final String  COLUMN_ID="id";
    private static final String  COLUMN_POS="pos";
    private static final String  COLUMN_COMPANY="companyName";
    private static final String  COLUMN_MONTH="month";
    private static final String  COLUMN_YEAR="year";
    private static final String  COLUMN_RS="rs";
    private static final String  COLUMN_ENTRIES="entries";
    private static final String  COLUMN_TXN="txn";



    String CREATE_BANK_TABLE= "CREATE TABLE " + TABLE_BANK + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_POS+ " TEXT, " + COLUMN_COMPANY + " TEXT, " + COLUMN_MONTH + " TEXT, " + COLUMN_YEAR + " TEXT, " + COLUMN_TXN +
            " TEXT, "+ COLUMN_RS  +
            " REAL " +" )";

    String CREATE_spend_TABLE= "CREATE TABLE " + TABLE_spend + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_MONTH + " TEXT, " +  COLUMN_YEAR + " TEXT, " + COLUMN_ENTRIES + " REAL, " + COLUMN_RS +
            " REAL " +" )";

    MySqliteHandlerBank(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BANK_TABLE);
        sqLiteDatabase.execSQL(CREATE_spend_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_BANK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_spend);

        onCreate(sqLiteDatabase);


    }

    public void addBank(Bank bank)
    {
        SQLiteDatabase sqLiteDatabase=MySqliteHandlerBank.this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_POS,bank.getPos());
        values.put(COLUMN_COMPANY,bank.getCompanyName());
        values.put(COLUMN_MONTH,bank.getMonth());
        values.put(COLUMN_YEAR,bank.getYear());
        values.put(COLUMN_RS,bank.getRs());
        values.put(COLUMN_TXN,bank.getTxn());

        sqLiteDatabase.insert(TABLE_BANK,null,values);
        sqLiteDatabase.close();
    }

    public Bank getBank(int id)
    {
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.query(TABLE_BANK,new String[] {COLUMN_ID,COLUMN_POS,COLUMN_COMPANY,COLUMN_MONTH,COLUMN_YEAR,COLUMN_TXN,COLUMN_RS},COLUMN_ID + " = ? "
                , new String[] {String.valueOf(id)}, null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        Bank bank=new Bank(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getDouble(6));

        return bank;
    }

    public List<Bank> getAllBanks()
    {
        List<Bank> bankList=new ArrayList<>();

        String selectAllQuery="SELECT * FROM " + TABLE_BANK;
        SQLiteDatabase database= this.getWritableDatabase();
        Cursor cursor=database.rawQuery(selectAllQuery,null);
        if(cursor.moveToFirst())
        {
            do{
                Bank bank=new Bank();
                bank.setId(Integer.parseInt(cursor.getString(0)));
                bank.setPos(cursor.getString(1));
                bank.setCompanyName(cursor.getString(2));
                bank.setMonth(cursor.getString(3));
                bank.setYear(cursor.getString(4));
                bank.setTxn(cursor.getString(5));
                bank.setRs(Double.parseDouble(cursor.getString(6)));

                bankList.add(bank);
            }while (cursor.moveToNext());
        }

        return bankList;
    }

    public List<Bank> getAllBanks(String month, String year)
    {
        List<Bank> bankList=new ArrayList<>();

        String selectAllQuery="SELECT * FROM " + TABLE_BANK + " WHERE " + COLUMN_MONTH + " = '" +  month + "' and " +COLUMN_YEAR + " = '" + year+"'";
        SQLiteDatabase database= this.getWritableDatabase();
        Cursor cursor=database.rawQuery(selectAllQuery,null);
        if(cursor.moveToFirst())
        {
            do{
                Bank bank=new Bank();
                if(monthlySpend.context!=null)
                    Toast.makeText(monthlySpend.context,cursor.getString(2),Toast.LENGTH_SHORT).show();
                bank.setId(Integer.parseInt(cursor.getString(0)));
                bank.setPos(cursor.getString(1));
                bank.setCompanyName(cursor.getString(2));
                bank.setMonth(cursor.getString(3));
                bank.setYear(cursor.getString(4));
                bank.setTxn(cursor.getString(5));
                bank.setRs(Double.parseDouble(cursor.getString(6)));

                bankList.add(bank);
            }while (cursor.moveToNext());
        }

        return bankList;
    }

    public int updateBank(Bank bank)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_POS,bank.getPos());
        values.put(COLUMN_COMPANY,bank.getCompanyName());
        values.put(COLUMN_MONTH,bank.getMonth());
        values.put(COLUMN_YEAR,bank.getYear());
        values.put(COLUMN_RS,bank.getRs());
        values.put(COLUMN_TXN,bank.getTxn());

        return  database.update(TABLE_BANK,values,COLUMN_ID + " = ? ",new String[] { String.valueOf(bank.getId())});
    }

    public void deleteBank(Bank bank)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        database.delete(TABLE_BANK,COLUMN_ID +" = ? ",new String[] {String.valueOf(bank.getId())});
        database.close();
    }

    public int getBankCount()
    {
        String bankCountQuery="SELECT * FROM " + TABLE_BANK;
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery(bankCountQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    public void addspend(spend s)
    {
        SQLiteDatabase sqLiteDatabase=MySqliteHandlerBank.this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_MONTH,s.getMonth());
        values.put(COLUMN_YEAR,s.getYear());
        values.put(COLUMN_ENTRIES,s.getEntries());
        values.put(COLUMN_RS,s.getRs());
        sqLiteDatabase.insert(TABLE_spend,null,values);
        sqLiteDatabase.close();
    }

    public spend getSpend(int id)
    {
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.query(TABLE_spend,new String[] {COLUMN_ID,COLUMN_MONTH,COLUMN_YEAR,COLUMN_ENTRIES,COLUMN_RS},COLUMN_ID + " = ? "
                , new String[] {String.valueOf(id)}, null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        spend s=new spend(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4));
        return s;
    }

    public spend getSpendByMonthYear(String month,String year)
    {
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.query(TABLE_spend,new String[] {COLUMN_ID,COLUMN_MONTH,COLUMN_YEAR,COLUMN_ENTRIES,COLUMN_RS},COLUMN_MONTH + " = ? and " + COLUMN_YEAR + " = ? "
                , new String[] {month,year}, null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        if(cursor.getCount()<=0)
        {
            return new spend();
        }

        spend s=new spend(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4));
        return s;
    }

    public List<spend> getAllspends()
    {
        List<spend> spendList=new ArrayList<>();

        String selectAllQuery="SELECT * FROM " + TABLE_spend;
        SQLiteDatabase database= this.getWritableDatabase();
        Cursor cursor=database.rawQuery(selectAllQuery,null);
        if(cursor.moveToFirst())
        {
            do{
                spend s=new spend();
                s.setId(Integer.parseInt(cursor.getString(0)));
                s.setMonth(cursor.getString(1));
                s.setYear(cursor.getString(2));
                s.setEntries(cursor.getDouble(3));
                s.setRs(cursor.getDouble(4));

                spendList.add(s);
            }while (cursor.moveToNext());
        }

        return spendList;
    }

    public int updateSpend(spend s)
    {
        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        spend s1=getSpendByMonthYear(s.getMonth(),s.getYear());
//        values.put(COLUMN_MONTH,s.getMonth());
//        values.put(COLUMN_YEAR,s.getYear());
        values.put(COLUMN_ENTRIES,s.getEntries()+1);
        values.put(COLUMN_RS,s.getRs());
        if(s1.getRs()==null || s1.getEntries()==null)
        {
            s1.setRs(0d);
            s1.setEntries(0d);
        }
        values.put(COLUMN_RS,s.getRs()+s1.getRs());
        values.put(COLUMN_ENTRIES,s.getEntries()+s1.getEntries());

        int a=database.update(TABLE_spend,values,COLUMN_MONTH + " = ? and " + COLUMN_YEAR + " = ? ",new String[] { String.valueOf(s.getMonth()),String.valueOf(s.getYear())});
        if(a==0)
        {
            addspend(s);
            return a;
        }
        else
        {
            return a;
        }
    }

    public void deleteSpend(spend s)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        database.delete(TABLE_spend,COLUMN_ID +" = ? ",new String[] {String.valueOf(s.getId())});
        database.close();
    }

    public int getSpendCount()
    {
        String spendCountQuery="SELECT * FROM " + TABLE_spend;
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery(spendCountQuery,null);
        cursor.close();

        return cursor.getCount();
    }

}
