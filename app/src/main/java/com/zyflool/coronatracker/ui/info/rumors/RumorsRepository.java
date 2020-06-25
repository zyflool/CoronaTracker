package com.zyflool.coronatracker.ui.info.rumors;

import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.RumorsResultResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RumorsRepository {

    private static RumorsRepository INSTANCE;

    public static RumorsRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RumorsRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new RumorsRepository();
                }
            }
        }
        return INSTANCE;
    }

    private RumorsRepository() {

    }

    public Observable<List<RumorsResultResponse.ResultsBean>> getRumors(int rumorType, int page, int num) {
        return NetUtil.getInstance().getApi().getRumors(rumorType, page, num)
                .subscribeOn(Schedulers.io())
                .map(rumorsResultResponse -> rumorsResultResponse.getResults());
    }
}
