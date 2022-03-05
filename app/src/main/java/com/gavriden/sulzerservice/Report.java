package com.gavriden.sulzerservice;

public class Report {

    private String Pump;
    private int Sync_status;

    Report(String Pump, int Sync_status){

        this.setPump(Pump);
        this.setSync_status(Sync_status);

    }

    public String getPump() {
        return Pump;
    }

    public void setPump(String pump) {
        Pump = pump;
    }

    public int getSync_status() {
        return Sync_status;
    }

    public void setSync_status(int sync_status) {
        Sync_status = sync_status;
    }
}
