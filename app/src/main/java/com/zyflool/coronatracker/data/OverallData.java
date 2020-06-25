package com.zyflool.coronatracker.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "OverallData")
public final class OverallData {

    @PrimaryKey
    @ColumnInfo(name = "updatetime")
    @NonNull
    private final String updatetime;

    @ColumnInfo(name = "inland")
    private CoronaData inlandData;

    @ColumnInfo(name= "world")
    private CoronaData worldData;

    public OverallData(@NonNull String updatetime, CoronaData inlandData, CoronaData worldData) {
        this.updatetime = updatetime;
        this.inlandData = inlandData;
        this.worldData = worldData;
    }

    @NonNull
    public String getUpdatetime() {
        return updatetime;
    }

    public CoronaData getInlandData() {
        return inlandData;
    }

    public void setInlandData(CoronaData inlandData) {
        this.inlandData = inlandData;
    }

    public CoronaData getWorldData() {
        return worldData;
    }

    public void setWorldData(CoronaData worldData) {
        this.worldData = worldData;
    }
}
