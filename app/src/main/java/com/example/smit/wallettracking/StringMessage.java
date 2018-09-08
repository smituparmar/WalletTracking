package com.example.smit.wallettracking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class StringMessage {
    private String POS,RS,message,txn,companyName,UPI;
    private Double Rs;
    private ArrayList<String> msg;
    private Date date;

    public Double checkNumeric(String test)
    {
        Double output;
        try {
            output = Double.parseDouble(test);
        } catch (Exception e) {
            test = test.substring(2);
            //  System.out.println(this.RS);
            output = Double.parseDouble(test);
        }
        return output;
    }

    public String getUPI() {
        return UPI;
    }

    public String getPOS()
    {
        return POS;
    }

    public String getRS()
    {
        return RS;
    }

    public Double getRs()
    {
        return Rs;
    }

    public Date getDate() {
        return date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTxn() {
        return txn;
    }

    public StringMessage(String s)
    {
        message=s;
        POS="\0";
        RS="\0";
        Rs=0d;
        date=null;
        txn="\0";
        companyName="\0";
        UPI="\0";
    }

    public void preprocessing(){

        String []message1=message.split(" ");
        msg = new ArrayList<String>(message1.length);

        for(String s:message1)
        {
            if(s !="  " || s!=" ") {
                // System.out.println(s);
                msg.add(s);
            }
        }

        if(msg.contains("purchase"))
        {
            checkPurchase();
        }
        else if(msg.contains("debited"))
        {
            try {
                checkDebited();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void checkPurchase()
    {
        int p=msg.indexOf("POS");
        if(p!=-1) {
            this.POS = msg.get(p + 2);
            //System.out.println(this.POS);
            int r = msg.indexOf("worth");
            if (r != -1) {
                this.RS = msg.get(r + 1);
                // System.out.println(this.RS);
                Rs=checkNumeric(RS);
            }
            int at=msg.indexOf("at");
            if(at!=-1) {
                String company = msg.get(at + 1);
                int txnNumber=msg.indexOf("txn#");
                while (at<txnNumber-1) {
                    companyName += company+" ";
                    at++;
                    company = msg.get(at + 1);
                }
            }
            int txnNumber=msg.indexOf("txn#");
            if(txnNumber!=-1)
            {
                txn=msg.get(txnNumber+1);
                txn=txn.substring(0,txn.length()-3);
            }
        }
    }

    private void checkDebited() throws ParseException {
        int d=msg.indexOf("debited");
        if(d!=-1)
        {
            RS=msg.get(d+2);
            Rs=checkNumeric(RS);
            int dateCheck=msg.indexOf("Date");
            if(dateCheck!=-1)
            {
                String dateString=msg.get(dateCheck+1)+" "+msg.get(dateCheck+2);
                try {
                    date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            int u=msg.indexOf("UPI");
            if(u!=-1)
            {
                UPI=msg.get(u+2);
                UPI=UPI.substring(0,UPI.length()-9);
            }
        }
    }

}

