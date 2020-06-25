package com.zyflool.coronatracker.ui.info.rumors;

import android.util.Log;

import com.zyflool.coronatracker.data.News;
import com.zyflool.coronatracker.data.Rumors;
import com.zyflool.coronatracker.net.NewsResultResponse;
import com.zyflool.coronatracker.net.RumorsResultResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RumorsPresenter implements RumorsContract.RumorsPresenter {

    private RumorsContract.RumorsView mView;
    private RumorsRepository mRepository;

    private static int page;
    private int num = 20;
    private final int rumorType = 1;

    public RumorsPresenter(RumorsContract.RumorsView view, RumorsRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getRumors(boolean isFirstPage) {
        if ( isFirstPage )
            page = 1;
        else page = page + 1;

        mRepository.getRumors(rumorType, page, num)
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RumorsResultResponse.ResultsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RumorsResultResponse.ResultsBean> resultsBeans) {
                        List<Rumors> rumorsList = new ArrayList<>();
                        for (RumorsResultResponse.ResultsBean r : resultsBeans) {
                            rumorsList.add(new Rumors(r.getTitle(), r.getMainSummary(), r.getBody()));
                        }
                        if ( page == 1 )
                            mView.showRumors(rumorsList);
                        else
                            mView.showMoreRumors(rumorsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("RumorsPresenter", "getRumors remote Fail");
                        mView.showError("网络错误，请检查网络连接或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
