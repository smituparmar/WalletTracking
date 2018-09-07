package com.example.smit.wallettracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    static int status=0;
    SmsReceiver smsReceiver;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView) findViewById(R.id.textView);

        textView.setText(context.toString());


    }


    public void showToast(Context context)
    {
        Toast.makeText(context,this.toString(),Toast.LENGTH_SHORT).show();
    }


    public void setTextInView(String s)
    {
        textView=(TextView) findViewById(R.id.textView);
        textView.setText(s);
    }
}
