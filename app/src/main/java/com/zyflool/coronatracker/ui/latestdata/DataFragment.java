package com.zyflool.coronatracker.ui.latestdata;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.ui.latestdata.inland.InlandFragment;
import com.zyflool.coronatracker.ui.latestdata.world.WorldFragment;

public class DataFragment extends Fragment {

    private TabLayout mDataTl;
    private ViewPager mDataVp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        mDataTl = view.findViewById(R.id.tl_data);
        mDataVp = view.findViewById(R.id.vp_data);

        mDataTl.setBackgroundResource(R.color.primaryLightColor);

        mDataTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mDataVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mDataVp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new InlandFragment();
                    case 1:
                        return new WorldFragment();
                }
                return new InlandFragment();
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mDataTl.setupWithViewPager(mDataVp);
        mDataTl.getTabAt(0).setText("国内");
        mDataTl.getTabAt(1).setText("国际");

        return view;
    }
}
