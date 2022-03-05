package com.gavriden.sulzerservice;

public class UnsentReports {

    private String Pump;
    private String Date;

    UnsentReports(String Pump, String Date){

        this.setPump(Pump);
        this.setDate(Date);

    }

    public void changeText(String text){

        Pump = text;

    }

    public String getPump() {
        return Pump;
    }

    public void setPump(String pump) {
        Pump = pump;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) { Date = date; }
}