package com.zyflool.coronatracker.ui.latestdata.world;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zyflool.coronatracker.MainRepository;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.OverallResultResponse;
import com.zyflool.coronatracker.util.AppExecutors;
import com.zyflool.coronatracker.util.Constants;
import com.zyflool.coronatracker.util.DataDisplayView;
import com.zyflool.coronatracker.util.SPUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WorldFragment extends Fragment {

    private SPUtils spUtils;

    private MainRepository mainRepository;
    private CountryLocalDataSource mCountryLocalDataSource;

    private SearchView mSv;
    private DataDisplayView mDdv;
    private View mView;
    private FloatingActionButton mFab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainRepository = MainRepository.getInstance();
        mCountryLocalDataSource = CountryLocalDataSource.getInstance(
                CountryDataBase.getInstance(getContext()).CountryDao(),
                new AppExecutors());
        spUtils = SPUtils.getInstance("OverAll");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);

        mView = view.findViewById(R.id.view_world);
        mSv = view.findViewById(R.id.sv_world);
        mDdv = view.findViewById(R.id.ddv_world);
        mFab = view.findViewById(R.id.fab_world);

        mFab.setOnClickListener(v -> getOverallRemote());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        initData();

        return view;
    }

    public void initData() {
        if ( spUtils.getString("updateTime","").length() == 0 )
            getOverallRemote();
        else
            mDdv.setData(spUtils.getString("updateTime"),
                    spUtils.getInt("WorldCurrentConfirmedCount"),
                    spUtils.getInt("WorldConfirmedCount"),
                    spUtils.getInt("WorldCuredCount"),
                    spUtils.getInt("WorldDeadCount"));

        for (String s :Constants.CountryName )
            mCountryLocalDataSource.insertCountry(s);
    }

    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    public void getOverallRemote() {
        NetUtil.getInstance().getApi().getOverAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(new Observer<OverallResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OverallResultResponse overallResultResponse) {
                        OverallResultResponse.ResultsBean t =
                                overallResultResponse.getResults().get(0);

                        spUtils.put("WorldCurrentConfirmedCount", t.getGlobalStatistics().getCurrentConfirmedCount());
                        spUtils.put("WorldConfirmedCount", t.getGlobalStatistics().getConfirmedCount());
                        spUtils.put("WorldCuredCount",t.getGlobalStatistics().getCuredCount());
                        spUtils.put("WorldDeadCount", t.getGlobalStatistics().getDeadCount());

                        mDdv.setData(t.getUpdateTime()+"",
                                t.getGlobalStatistics().getCurrentConfirmedCount(),
                                t.getGlobalStatistics().getConfirmedCount(),
                                t.getGlobalStatistics().getCuredCount(),
                                t.getGlobalStatistics().getDeadCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("worldFragment","data fail");
                        showError("获取数据失败，请检查网络或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
