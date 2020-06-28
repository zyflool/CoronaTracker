package com.zyflool.coronatracker.ui.latestdata.world;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.zyflool.coronatracker.MainRepository;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.CoronaData;
import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.OverallResultResponse;
import com.zyflool.coronatracker.util.AppExecutors;
import com.zyflool.coronatracker.util.Constants;
import com.zyflool.coronatracker.util.DataDisplayView;
import com.zyflool.coronatracker.util.SPUtils;
import com.zyflool.coronatracker.util.Utils;

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

        mDdv.setFabListener(v -> getOverallRemote());

        mView.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));

        initData();

        return view;
    }

    public void initData() {
        if ( spUtils.getInt("InlandCurrentConfirmedCount",0) == 0 )
            getOverallRemote();
        else
            mDdv.setData(
                    new CoronaData(spUtils.getInt("WorldCurrentConfirmedCount"),
                            spUtils.getInt("WorldConfirmedCount"),
                            spUtils.getInt("WorldCuredCount"),
                            spUtils.getInt("WorldDeadCount"),
                            spUtils.getInt("WorldCurrentConfirmedIncr"),
                            spUtils.getInt("WorldConfirmedIncr"),
                            spUtils.getInt("WorldCuredIncr"),
                            spUtils.getInt("WorldDeadIncr")
                    ));

        for (String s :Constants.CountryName )
            mCountryLocalDataSource.insertCountry(s);
    }



    public void getOverallRemote() {
        NetUtil.getInstance().getApi().getOverAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OverallResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OverallResultResponse overallResultResponse) {
                        OverallResultResponse.ResultsBean t =
                                overallResultResponse.getResults().get(0);

                        Utils.putDataToSp(spUtils, t);

                        mDdv.setData(new CoronaData(
                                t.getGlobalStatistics().getCurrentConfirmedCount(),
                                t.getGlobalStatistics().getConfirmedCount(),
                                t.getGlobalStatistics().getCuredCount(),
                                t.getGlobalStatistics().getDeadCount(),
                                t.getGlobalStatistics().getCurrentConfirmedIncr(),
                                t.getGlobalStatistics().getConfirmedIncr(),
                                t.getGlobalStatistics().getCuredIncr(),
                                t.getGlobalStatistics().getDeadIncr()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("worldFragment","data fail");
                        initData();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
