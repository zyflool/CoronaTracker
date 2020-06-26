package com.zyflool.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.zyflool.coronatracker.ui.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout MainFl;

    private MainRepository mainRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRepository = MainRepository.getInstance();
        mainRepository.getOverall();

        MainFl = findViewById(R.id.fl_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, new HomeFragment()).commit();

    }


}
