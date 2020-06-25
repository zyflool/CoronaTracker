package com.zyflool.coronatracker.ui.info.news;

import com.zyflool.coronatracker.data.News;

import java.util.List;

public interface NewsContract {

    interface NewsView {

        void showNews(List<News> newsList);

        void showMoreNews(List<News> newsList);

        void showError(String error);

    }

    interface NewsPresenter {

        void getNews(boolean isFirstPage);

    }

}
