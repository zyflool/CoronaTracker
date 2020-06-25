package com.zyflool.coronatracker.ui.info.rumors;

import com.zyflool.coronatracker.data.Rumors;

import java.util.List;

public interface RumorsContract {

    interface RumorsView {

        void showRumors(List<Rumors> newsList);

        void showMoreRumors(List<Rumors> newsList);

        void showError(String error);

    }

    interface RumorsPresenter {

        void getRumors(boolean isFirstPage);

    }

}
