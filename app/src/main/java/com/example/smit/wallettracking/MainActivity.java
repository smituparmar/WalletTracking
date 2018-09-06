package com.example.smit.wallettracking;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public MainActivity returnCurreentInstance()
    {
        return this;
    }

    public void setTextInView(String s)
    {
        textView=(TextView) findViewById(R.id.textView);
        String s1=textView.getText().toString();
        textView.setText(s);
        Toast.makeText(this,s+" Hello  "+s1,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,s+" Hello  "+s1,Toast.LENGTH_SHORT).show();
    }
}
