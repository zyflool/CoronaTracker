package com.zyflool.coronatracker.data;

public class TimeLine {

    private String time;
    private int num;

    public TimeLine(String time, int num) {
        this.time = time;
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
