package com.example.smit.wallettracking;

public class Bank {

    private int id;
    private String pos,companyName,month,year,txn;
    private Double rs;

    Bank(String pos,String companyName,String month,String year,String txn,Double rs)
    {
        this.pos=pos;
        this.companyName=companyName;
        this.month=month;
        this.year=year;
        this.txn=txn;
        this.rs=rs;
    }

    Bank(int id,String pos,String companyName,String month,String year,String txn,Double rs)
    {
        this.id=id;
        this.pos=pos;
        this.companyName=companyName;
        this.month=month;
        this.year=year;
        this.txn=txn;
        this.rs=rs;
    }

    Bank()
    {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getRs() {
        return rs;
    }

    public void setRs(Double rs) {
        this.rs = rs;
    }

    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }
}

