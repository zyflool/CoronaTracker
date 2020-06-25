package com.zyflool.coronatracker.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "rumors")
public final class Rumors {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private final String uuid;
    @ColumnInfo(name = "title")
    private final String title;
    @ColumnInfo(name = "mainSummary")
    private final String mainSummary;
    @ColumnInfo(name = "body")
    private final String body;

    @Ignore
    public Rumors(String title, String mainSummary, String body) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.mainSummary = mainSummary;
        this.body = body;
    }

    public Rumors(String uuid, String title, String mainSummary, String body) {
        this.uuid = uuid;
        this.title = title;
        this.mainSummary = mainSummary;
        this.body = body;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getMainSummary() {
        return mainSummary;
    }

    public String getBody() {
        return body;
    }
}
