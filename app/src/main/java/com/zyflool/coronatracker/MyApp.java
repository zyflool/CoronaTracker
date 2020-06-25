package com.zyflool.coronatracker;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {

    private static Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this.getApplicationContext();
        Fresco.initialize(this);
    }


    public static Context getAppContext() {
        return applicationContext;

    }

}