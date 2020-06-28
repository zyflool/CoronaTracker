package com.zyflool.coronatracker;

import android.util.Log;

import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.OverallResultResponse;
import com.zyflool.coronatracker.util.SPUtils;
import com.zyflool.coronatracker.util.Utils;

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

                        Utils.putDataToSp(spUtils, overallResultResponse.getResults().get(0));

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
