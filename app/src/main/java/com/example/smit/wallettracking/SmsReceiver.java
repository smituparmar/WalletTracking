package com.example.smit.wallettracking;;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Date;

import static android.content.Context.ACTIVITY_SERVICE;

public class SmsReceiver extends BroadcastReceiver {
    private String message;
    private static SharedPreferences sharedPreferences;
    private static int count=0;

    DatabaseHelper myDb;

    public static int getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle pudsBundle = intent.getExtras();
        final String format="format";
        sharedPreferences=context.getSharedPreferences("MessagePrefrence",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[])pdus[0]);

        // Todo : Show Message In Toast

        String mes = messages.getMessageBody();
        String phoneNumber = messages.getOriginatingAddress();
        String[] check = mes.split("\\s+");

        message=mes;

        StringMessage stringMessage=new StringMessage(message);
        stringMessage.preprocessing();
        new databaseClass(stringMessage).putInTables(context);

        Context context1=goToMainActivity();

        if(context1!=null) {
            MainActivity mainActivity = (MainActivity) context1;

            Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();

            mainActivity.finish();
            mainActivity.overridePendingTransition(0, 0);
            context.startActivity(mainActivity.getIntent());
            mainActivity.overridePendingTransition(0, 0);

//Variable defines for toast and textview
//            String pos = stringMessage.getPOS();
//            Double Rs = stringMessage.getRs();
//            Date date = stringMessage.getDate();
//            String company = stringMessage.getCompanyName();
//            String txn = stringMessage.getTxn();
//
//            String test=stringMessage.getPOS()+" "+stringMessage.getRs()+" "+stringMessage.getDate()+" "+stringMessage.getCompanyName()+
//                    " "+txn;
//
//            //Toast.makeText(context,stringMessage.getPOS()+" "+stringMessage.getRS(),Toast.LENGTH_LONG).show();
//            Double previous=stringMessage.checkNumeric(mainActivity.textView.getText().toString());
//
//            Double current=new Double(previous+Rs);
//
//            mainActivity.textView.setText(String.valueOf(current));
//
//            mainActivity.setDatabase(pos,Rs,date,company,txn);

        }
    }

    protected Context goToMainActivity()
    {
        return MainActivity.context;
    }
}

