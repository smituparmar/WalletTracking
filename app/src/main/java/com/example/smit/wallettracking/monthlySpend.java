package com.example.smit.wallettracking;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class monthlySpend extends AppCompatActivity {
    private EditText year;
    RecyclerView rv;
    public static Context context;
    ArrayList<String> companyName=new ArrayList<String>();
    ArrayList<Double> userRs=new ArrayList<Double>();
    ArrayList<String> userPos=new ArrayList<String>();
    ArrayList<String> currDate=new ArrayList<String>();
    ArrayList<Bank> bankByMonth=new ArrayList<Bank>();
    MySqliteHandlerBank sqldb;
    Button btnadd;
    Spinner spinner,monthspinner;
    int county=2016;
    String selectedmonth,selectedyear;
    Double sum=new Double(0);
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_monthly_spend);
       // year = (EditText)findViewById(R.id.selectyear);
        total = (TextView)findViewById(R.id.totalcost);
        spinner = (Spinner) findViewById(R.id.spinner);
        sqldb = new MySqliteHandlerBank(this);
        monthspinner = (Spinner)findViewById(R.id.monthspinner);
        monthspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedmonth = adapterView.getItemAtPosition(i).toString();
                displayData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // set dynmaic spinner
        String[] years = new String[]{
            "2018","2019"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(years));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedyear = adapterView.getItemAtPosition(i).toString();
                displayData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//  Add year in drop down list
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                county++;
//                plantsList.add(String.valueOf(county));
//                spinnerArrayAdapter.notifyDataSetChanged();
//            }
//        });

        rv = (RecyclerView)findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void displayData(){
        sum=0.0;
        CustomAdapter customAdapter = new CustomAdapter(companyName,userRs,userPos,currDate,monthlySpend.this);;
      //  Toast.makeText(monthlySpend.this, selectedmonth, Toast.LENGTH_SHORT).show();
        bankByMonth.clear();
        customAdapter.clear();
        customAdapter.notifyDataSetChanged();
        bankByMonth = (ArrayList<Bank>) sqldb.getAllBanks(selectedmonth,selectedyear);

        for(Bank b:bankByMonth) {
            companyName.add(b.getCompanyName());
            userPos.add(b.getPos());
            userRs.add(b.getRs());
            //currDate.add(b..getString(5));

        }
        Collections.reverse(companyName);
        Collections.reverse(userPos);
        Collections.reverse(userRs);

        for(Double d:userRs)
        {
            sum+=d;
        }
        total.setText(String.valueOf(sum));
        rv.setAdapter(customAdapter);
    }
}
