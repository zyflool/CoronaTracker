package com.zyflool.coronatracker.ui.latestdata.inland;

import com.zyflool.coronatracker.data.TimeLines;

import java.util.List;

public interface ProvinceContract {

    interface ProvinceView {
        void setChart(List<List<TimeLines>> dataList);

        void showError(String error);

    }

    interface ProvincePresenter {
        void getData(String LocationEng);
    }

}
