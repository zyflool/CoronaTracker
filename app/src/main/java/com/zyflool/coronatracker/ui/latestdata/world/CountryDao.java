package com.zyflool.coronatracker.ui.latestdata.world;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country WHERE countryName LIKE '%' || :name || '%' " )
    List<Country> searchCountry(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country country);

    @Query("DELETE FROM country")
    void deleteAll();
}
