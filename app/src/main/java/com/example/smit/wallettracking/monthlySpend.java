package com.example.smit.wallettracking;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class monthlySpend extends AppCompatActivity {
    private EditText month,year;
    RecyclerView rv;
    public static Context context;
    ArrayList<String> companyName=new ArrayList<String>();
    ArrayList<Double> userRs=new ArrayList<Double>();
    ArrayList<String> userPos=new ArrayList<String>();
    ArrayList<String> currDate=new ArrayList<String>();
    ArrayList<Bank> bankByMonth=new ArrayList<Bank>();
    MySqliteHandlerBank sqldb;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_monthly_spend);
        month = (EditText)findViewById(R.id.selectmonth);
        year = (EditText)findViewById(R.id.selectyear);
        sqldb = new MySqliteHandlerBank(this);

        rv = (RecyclerView)findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);
        displayData();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void displayData(){
        Toast.makeText(monthlySpend.this, month.getText().toString(), Toast.LENGTH_SHORT).show();

        bankByMonth = (ArrayList<Bank>) sqldb.getAllBanks(month.getText().toString(),year.getText().toString());

        for(Bank b:bankByMonth) {
            Toast.makeText(this, b.getCompanyName(), Toast.LENGTH_SHORT).show();
            companyName.add(b.getCompanyName());
            userPos.add(b.getPos());
            userRs.add(b.getRs());
            //currDate.add(b..getString(5));
        }
        Collections.reverse(companyName);
        Collections.reverse(userPos);
        Collections.reverse(userRs);

        CustomAdapter customAdapter = new CustomAdapter(companyName,userRs,userPos,currDate,monthlySpend.this);
        rv.setAdapter(customAdapter);
    }
}
