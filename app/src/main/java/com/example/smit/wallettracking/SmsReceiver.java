package com.example.smit.wallettracking;;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle pudsBundle = intent.getExtras();
        final String format="format";
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[])pdus[0]);
        // Log.i(TAG,  messages.getMessageBody());

        // Todo : Show Message In Toast

        String mes = messages.getMessageBody();
        String phoneNumber = messages.getOriginatingAddress();
        String[] check = mes.split("\\s+");

        message=mes;


       Toast.makeText(context,mes+" "+phoneNumber,Toast.LENGTH_SHORT).show();

  //      MainActivity mainActivity=new MainActivity().returnCurreentInstance();
//        mainActivity.setTextInView("message");

    }
}
