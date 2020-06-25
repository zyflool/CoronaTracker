package com.zyflool.coronatracker.ui.info.news;

import android.util.Log;

import com.zyflool.coronatracker.data.News;
import com.zyflool.coronatracker.net.NewsResultResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsPresenter implements NewsContract.NewsPresenter {

    private NewsContract.NewsView mView;
    private NewsRepository mRepository;

    private static int page;
    private int num = 20;

    public NewsPresenter(NewsContract.NewsView view, NewsRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getNews(boolean isFirstPage) {
        if ( isFirstPage )
            page = 1;
        else page = page + 1;

        mRepository.getNews(page, num)
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsResultResponse.ResultsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NewsResultResponse.ResultsBean> resultsBeans) {
                        Log.e("NewsPresenter","get remote News"+page+" success");
                        List<News> newsList = new ArrayList<>();
                        for (NewsResultResponse.ResultsBean r : resultsBeans) {
                            newsList.add(new News(r.getPubDate(), r.getTitle(), r.getSummary(),
                                    r.getInfoSource(), r.getSourceUrl()));
                        }
                        if ( page == 1 )
                            mView.showNews(newsList);
                        else
                            mView.showMoreNews(newsList);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("NewsPresenter", "get remote News"+page+" Fail");
                        mView.showError("网络错误，请检查网络连接或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
