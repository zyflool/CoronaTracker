package com.zyflool.coronatracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.ui.info.InfoFragment;
import com.zyflool.coronatracker.ui.latestdata.DataFragment;
import com.zyflool.coronatracker.util.NoScrollViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static HomeFragment Instance = new HomeFragment();

    private NoScrollViewPager DataVp;
    private BottomNavigationView BottomNav;

    private  HomeFragment() { }

    public static HomeFragment newInstance () {
        return Instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BottomNav = view.findViewById(R.id.bnv_home);
        DataVp = view.findViewById(R.id.nsvp_home);

        initView();

        return view;
    }

    private void initView() {

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager());


        DataVp.setAdapter(fragmentAdapter);

        setNavigation();

    }

    public void setNavigation() {


        BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchMenu(item);
                return true;
            }
        });


        DataVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BottomNav.getMenu().getItem(position).setChecked(true);
                switchMenu(BottomNav.getMenu().getItem(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 导航栏切换方法
     */
    private void switchMenu(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_data:
                DataVp.setCurrentItem(0);
                break;
            case R.id.menu_news:
                DataVp.setCurrentItem(1);
                break;
            default:
        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new ArrayList<>();

        public FragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            // 加载初始化Fragment
            mFragments.add(new DataFragment());
            mFragments.add(InfoFragment.newInstance());
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = mFragments.get(0);
                    break;
                case 1:
                    fragment = mFragments.get(1);
                    break;
                default:
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
