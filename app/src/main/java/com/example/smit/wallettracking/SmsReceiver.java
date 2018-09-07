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

import static android.content.Context.ACTIVITY_SERVICE;

public class SmsReceiver extends BroadcastReceiver {
    private String message;
    private static SharedPreferences sharedPreferences;
    private static int count=0;

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
        // Log.i(TAG,  messages.getMessageBody());

        // Todo : Show Message In Toast

        String mes = messages.getMessageBody();
        String phoneNumber = messages.getOriginatingAddress();
        String[] check = mes.split("\\s+");

        message=mes;


       Toast.makeText(context,mes+" "+phoneNumber+" "+sharedPreferences.toString(),Toast.LENGTH_SHORT).show();
       count++;

        Context context1=MainActivity.context;
        if(context1!=null) {
            MainActivity mainActivity = (MainActivity) context1;

            StringMessage stringMessage=new StringMessage(message);
            stringMessage.preprocessing();
            String test=stringMessage.getPOS()+" "+stringMessage.getRs()+" "+stringMessage.getDate()+" "+stringMessage.getCompanyName()+
                    " "+stringMessage.getTxn();
            //Toast.makeText(context,stringMessage.getPOS()+" "+stringMessage.getRS(),Toast.LENGTH_LONG).show();
            mainActivity.setTextInView(test);

        }
    }
}

