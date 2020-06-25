package com.zyflool.coronatracker.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "news")
public final class News {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private final String uuid;
    @ColumnInfo(name = "pudDate")
    private final String pubDate;
    @ColumnInfo(name = "title")
    private final String title;
    @ColumnInfo(name = "summary")
    private final String summary;
    @ColumnInfo(name = "infoSource")
    private final String infoSource;
    @ColumnInfo(name = "sourceUrl")
    private final String sourceUrl;

    public News(String uuid, String pubDate, String title, String summary, String infoSource, String sourceUrl) {
        this.uuid = uuid;
        this.pubDate = pubDate;
        this.title = title;
        this.summary = summary;
        this.infoSource = infoSource;
        this.sourceUrl = sourceUrl;
    }

    @Ignore
    public News(String pubDate, String title, String summary, String infoSource, String sourceUrl) {
        this.uuid = UUID.randomUUID().toString();
        this.pubDate = pubDate;
        this.title = title;
        this.summary = summary;
        this.infoSource = infoSource;
        this.sourceUrl = sourceUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }
}
