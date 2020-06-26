package com.zyflool.coronatracker;

import android.content.Context;
import android.util.Log;

import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.OverallResultResponse;
import com.zyflool.coronatracker.util.SPUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private static MainRepository INSTANCE;
    private SPUtils spUtils = SPUtils.getInstance("OverAll");

    public static MainRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new MainRepository();
                }
            }
        }
        return INSTANCE;
    }

    private MainRepository() {
    }

    public void getOverall() {
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
                        Log.e("MainRepository", "getOverall success");

                        OverallResultResponse.ResultsBean t = overallResultResponse.getResults().get(0);

                        spUtils.put("updateTime", t.getUpdateTime()+"");

                        spUtils.put("InlandCurrentConfirmedCount", t.getCurrentConfirmedCount());
                        spUtils.put("InlandConfirmedCount", t.getConfirmedCount());
                        spUtils.put("InlandCuredCount",t.getCuredCount());
                        spUtils.put("InlandDeadCount", t.getDeadCount());
                        spUtils.put("InlandImportedCount",t.getSuspectedCount());
                        spUtils.put("InlandAsymptomaticCount", t.getSeriousCount());

                        spUtils.put("InlandCurrentConfirmedIncr", t.getCurrentConfirmedIncr());
                        spUtils.put("InlandConfirmedIncr", t.getConfirmedIncr());
                        spUtils.put("InlandCuredIncr", t.getCuredIncr());
                        spUtils.put("InlandDeadIncr", t.getDeadIncr());
                        spUtils.put("InlandImportedIncr",t.getSuspectedIncr());
                        spUtils.put("InlandAsymptomaticIncr", t.getSeriousIncr());

                        spUtils.put("WorldCurrentConfirmedCount", t.getGlobalStatistics().getCurrentConfirmedCount());
                        spUtils.put("WorldConfirmedCount", t.getGlobalStatistics().getConfirmedCount());
                        spUtils.put("WorldCuredCount",t.getGlobalStatistics().getCuredCount());
                        spUtils.put("WorldDeadCount", t.getGlobalStatistics().getDeadCount());

                        spUtils.put("WorldCurrentConfirmedIncr", t.getGlobalStatistics().getCurrentConfirmedIncr());
                        spUtils.put("WorldConfirmedIncr", t.getGlobalStatistics().getConfirmedIncr());
                        spUtils.put("WorldCuredIncr", t.getGlobalStatistics().getCuredIncr());
                        spUtils.put("WorldDeadIncr", t.getGlobalStatistics().getDeadIncr());


                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("MainRepository", "getOverall Fail");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
