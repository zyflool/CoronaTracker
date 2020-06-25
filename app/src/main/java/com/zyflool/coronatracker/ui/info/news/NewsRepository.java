package com.zyflool.coronatracker.ui.info.news;

import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.NewsResultResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {

    private static NewsRepository INSTANCE;

    public static NewsRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (NewsRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new NewsRepository();
                }
            }
        }
        return INSTANCE;
    }

    private NewsRepository() {
    }

    public Observable<List<NewsResultResponse.ResultsBean>> getNews(int page, int num) {
        return NetUtil.getInstance().getApi().getNews(null, page, num)
                .subscribeOn(Schedulers.io())
                .map(resultResponse -> resultResponse.getResults());
    }

}
