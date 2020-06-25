package com.zyflool.coronatracker.ui.latestdata.world;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =  {Country.class}, version = 1 , exportSchema = false)
public abstract class CountryDataBase extends RoomDatabase {

    private static CountryDataBase INSTANCE;

    private static final Object sLock = new Object();

    public abstract CountryDao CountryDao();

    public static CountryDataBase getInstance(Context context) {
        synchronized (sLock) {
            if ( INSTANCE == null ) {
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                        CountryDataBase.class, "Country.db").build();
            }
            return INSTANCE;
        }
    }
}
