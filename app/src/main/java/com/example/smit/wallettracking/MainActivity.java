package com.example.smit.wallettracking;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    MySqliteHandlerBank sqldb;
    TextView textView,textView2;
    static int status=0;
    SmsReceiver smsReceiver;
    public static Context context;
    ArrayList<String> companyName=new ArrayList<String>();
    ArrayList<Double> userRs=new ArrayList<Double>();
    ArrayList<String> userPos=new ArrayList<String>();
    ArrayList<String> currDate=new ArrayList<String>();
    ArrayList<Bank> bankByMonth=new ArrayList<Bank>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myDb = new DatabaseHelper(this);
        sqldb = new MySqliteHandlerBank(this);

        textView=(TextView) findViewById(R.id.textView);
        textView2=(TextView) findViewById(R.id.textView2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(companyName,userRs,userPos,currDate,MainActivity.this);
        recyclerView.setAdapter(customAdapter);

        // Pre load database
        Date date = new Date() ;
        String month  = (String) DateFormat.format("MMM",  date);
        String year   = (String) DateFormat.format("yyyy", date);
       // Cursor cursor=myDb.getAllData();
        bankByMonth = (ArrayList<Bank>) sqldb.getAllBanks(month,year);

        for(Bank b:bankByMonth) {
            companyName.add(b.getCompanyName());
            userPos.add(b.getPos());
            userRs.add(b.getRs());
            //currDate.add(b..getString(5));
        }

        Double sum=new Double(0);
        for(Double d:userRs)
        {
            sum+=d;
        }

        textView.setText(String.valueOf(sum));

        customAdapter=new CustomAdapter(companyName,userRs,userPos,currDate,MainActivity.this);
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));

    }

    public void showToast(Context context)
    {
        Toast.makeText(context,this.toString(),Toast.LENGTH_SHORT).show();
    }

    public void setTextInView(TextView text,String s)
    {
        text=(TextView) findViewById(R.id.textView);
        text.setText(s);
    }

    public void setDatabase(String pos, Double rs, Date date, String company, String txn){
        boolean isInserted = myDb.insertData(pos,rs,txn,company,date,"prit");
        if (isInserted==true) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            companyName.add(company);
            userRs.add(rs);
            userPos.add(pos);
            currDate.add(String.valueOf(date));
            CustomAdapter customAdapter = new CustomAdapter(companyName,userRs,userPos,currDate,MainActivity.this);
            recyclerView.setAdapter(customAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL));
        }
       else
            Toast.makeText(MainActivity.this,"Data Not Inserted", Toast.LENGTH_LONG).show();

        viewAll();
    }

    public void viewAll(){
        Cursor res =  myDb.getAllData();
        if(res.getCount()==0) {
            showMessage("Error","No data found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToLast();
        while (res.moveToPrevious()){
            buffer.append("ID :"+res.getString(0)+"\n");
            buffer.append("POS :"+res.getString(1)+"\n");
            buffer.append("RS :"+res.getString(2)+"\n");
            buffer.append("TXN :"+res.getString(3)+"\n");
            buffer.append("COMPANY :"+res.getString(4)+"\n");
            buffer.append("DATE :"+res.getString(5)+"\n");
            buffer.append("UPI :"+res.getString(6)+"\n\n");
        }
        //show all data
        showMessage("Message",buffer.toString());
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
