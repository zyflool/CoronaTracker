package com.zyflool.coronatracker.data;

public class News extends Information {

    private String time;
    private String sourceName;
    private String sourceUrl;

    public News(String pubDate, String title, String summary, String infoSource, String sourceUrl) {
        super(title, summary);
        this.time = pubDate;
        this.sourceName = infoSource;
        this.sourceUrl = sourceUrl;
    }

    public String getTime() {
        return time;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
