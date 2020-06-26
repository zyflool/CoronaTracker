package com.zyflool.coronatracker.ui.latestdata.inland;

import android.util.Log;

import com.zyflool.coronatracker.util.Utils;

import org.json.JSONException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class ProvincePresenter implements ProvinceContract.ProvincePresenter {

    private ProvinceContract.ProvinceView mView;
    private ProvinceRepository mRepository;

    private String source = "jhu";
    private boolean timelines = true;

    public ProvincePresenter(ProvinceContract.ProvinceView view, ProvinceRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getData(String LocationEng) {
        mRepository.getLocationData(source, LocationEng, timelines)
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.e("ProvincePresenter", "数据请求完成");
                        try {
                            mView.setChart(Utils.getDataFromJson(responseBody.string()));
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                            Log.e("ProvincePresenter", "数据请求完成，IOException");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("ProvincePresenter", "Location Data Fail");
                        mView.showError("获取数据失败，请检查网络或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
