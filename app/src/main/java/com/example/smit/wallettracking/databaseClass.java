package com.example.smit.wallettracking;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.format.DateFormat;

import java.util.Date;

public class databaseClass {
    private StringMessage stringMessage;

    databaseClass(StringMessage stringMessage)
    {
        this.stringMessage=stringMessage;
    }

    public void putInTables(Context context)
    {
        String pos = stringMessage.getPOS();
        Double rs = stringMessage.getRs();
        Date date = new Date() ;
        String month  = (String) DateFormat.format("MMM",  date);
        String year   = (String) DateFormat.format("yyyy", date); // 2013
        String company = stringMessage.getCompanyName();
        String txn = stringMessage.getTxn();

        MySqliteHandlerBank databaseHandler=new MySqliteHandlerBank(context);

        Bank bank=new Bank(pos,company,month,
                year,txn,rs);

        spend s=new spend(month,
                year,Double.parseDouble("1"),rs);

        databaseHandler.addBank(bank);
        databaseHandler.updateSpend(s);

    }
}
