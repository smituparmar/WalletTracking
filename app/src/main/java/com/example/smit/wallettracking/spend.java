package com.example.smit.wallettracking;

public class spend {

    private int id;
    private String month,year;
    private Double entries,rs;

    spend(String month,String year,Double entries,Double rs)
    {
        this.month=month;
        this.year=year;
        this.entries=entries;
        this.rs=rs;
    }

    spend(int id, String month,String year,Double entries,Double rs)
    {
        this.id=id;
        this.month=month;
        this.year=year;
        this.entries=entries;
        this.rs=rs;
    }

    spend()
    {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getEntries() {
        return entries;
    }

    public void setEntries(Double entries) {
        this.entries = entries;
    }

    public Double getRs() {
        return rs;
    }

    public void setRs(Double rs) {
        this.rs = rs;
    }


}

