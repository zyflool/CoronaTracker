package com.zyflool.coronatracker.data;


public class Rumors extends Information {

    private String body;

    public Rumors(String title, String summary, String body) {
        super(title, summary);
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
