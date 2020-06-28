package com.zyflool.coronatracker.ui.latestdata.inland;

import com.zyflool.coronatracker.data.TimeLine;
import com.zyflool.coronatracker.data.TimeLines;

import java.util.List;

public interface ProvinceContract {

    interface ProvinceView {
        void setChart(TimeLines dataList);

        void showError(String error);

    }

    interface ProvincePresenter {
        void getData(String LocationEng);
    }

}
