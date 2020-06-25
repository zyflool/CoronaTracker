package com.zyflool.coronatracker.ui.latestdata.world;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country")
public final class Country {

    @PrimaryKey
    @ColumnInfo(name = "countryName")
    @NonNull
    private final String name;

    public Country(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
