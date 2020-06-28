package com.zyflool.coronatracker.data;

public class Information {
    private String title;
    private String summary;

    public Information(String title , String summary ) {
        this.title = title;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
