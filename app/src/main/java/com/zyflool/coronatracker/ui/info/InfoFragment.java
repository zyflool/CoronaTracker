package com.zyflool.coronatracker.ui.info;

import android.os.Bundle;
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
import com.zyflool.coronatracker.ui.info.news.NewsFragment;
import com.zyflool.coronatracker.ui.info.rumors.RumorsFragment;

public class InfoFragment extends Fragment {

    private TabLayout mInfoTl;
    private ViewPager mInfoVp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        mInfoTl = view.findViewById(R.id.tl_info);

        mInfoVp = view.findViewById(R.id.vp_info);


        mInfoTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mInfoVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mInfoVp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new NewsFragment();
                    case 1:
                        return new RumorsFragment();
                }
                return new NewsFragment();
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mInfoTl.setupWithViewPager(mInfoVp);
        mInfoTl.getTabAt(0).setText("新闻");
        mInfoTl.getTabAt(1).setText("辟谣");

        return view;
    }
}
