package com.zyflool.coronatracker.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zyflool.coronatracker.data.CoronaData;
import com.zyflool.coronatracker.R;

public class DataDisplayView extends LinearLayout {

    private TextView timeTv;
    private TextView curConfirmedCountTv;
    private TextView confirmedCountTv;
    private TextView curedCountTv;
    private TextView deathCountTv;
    private TextView curConfirmedCountIncreaseTv;
    private TextView confirmedCountIncreaseTv;
    private TextView curedCountIncreaseTv;
    private TextView deathCountIncreaseTv;
    private TextView curConfirmedCountIncreaseTextTv;
    private TextView confirmedCountIncreaseTextTv;
    private TextView curedCountIncreaseTextTv;
    private TextView deathCountIncreaseTextTv;

    public DataDisplayView(Context context) {
        super(context);
        initView(context);
    }

    public DataDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DataDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public DataDisplayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.data_display_view, this, true);
        timeTv = findViewById(R.id.tv_latest_time);
        curConfirmedCountTv = findViewById(R.id.tv_cur_confirmed_count_number);
        curedCountTv = findViewById(R.id.tv_cured_count_number);
        confirmedCountTv = findViewById(R.id.tv_confirmed_count_number);
        deathCountTv = findViewById(R.id.tv_death_count_number);
        curConfirmedCountIncreaseTv = findViewById(R.id.tv_cur_confirmed_count_increase_number);
        curedCountIncreaseTv = findViewById(R.id.tv_cured_count_increase_number);
        confirmedCountIncreaseTv = findViewById(R.id.tv_confirmed_count_increase_number);
        deathCountIncreaseTv = findViewById(R.id.tv_death_count_increase_number);
        curConfirmedCountIncreaseTextTv = findViewById(R.id.tv_cur_confirmed_count_increase);
        confirmedCountIncreaseTextTv = findViewById(R.id.tv_confirmed_count_increase);
        curedCountIncreaseTextTv = findViewById(R.id.tv_cured_count_increase);
        deathCountIncreaseTextTv = findViewById(R.id.tv_death_count_increase);
    }

    public void setData(CoronaData data) {

        String timeFormat = getResources().getString(R.string.latest_time);
        String numberFormat = getResources().getString(R.string.number);
        String numberIncreaseFormat = getResources().getString(R.string.increase_number);

        String time = data.getTime();
        time = time.substring(0,time.length()-3);
        time = Utils.paserTime(Long.parseLong(time));
        timeTv.setText(String.format(timeFormat, time.substring(0,10)));

        curConfirmedCountTv.setText(String.format(numberFormat, data.getCurrentConfirmedCount()));
        confirmedCountTv.setText(String.format(numberFormat, data.getConfirmedCount()));
        curedCountTv.setText(String.format(numberFormat, data.getCuredCount()));
        deathCountTv.setText(String.format(numberFormat, data.getDeadCount()));

        curConfirmedCountIncreaseTv.setVisibility(VISIBLE);
        confirmedCountIncreaseTv.setVisibility(VISIBLE);
        curedCountIncreaseTv.setVisibility(VISIBLE);
        deathCountIncreaseTv.setVisibility(VISIBLE);

        curConfirmedCountIncreaseTextTv.setVisibility(VISIBLE);
        confirmedCountIncreaseTextTv.setVisibility(VISIBLE);
        curedCountIncreaseTextTv.setVisibility(VISIBLE);
        deathCountIncreaseTextTv.setVisibility(VISIBLE);

        curConfirmedCountIncreaseTv.setText(String.format(numberIncreaseFormat, data.getCurrentConfirmedIncr()));
        confirmedCountIncreaseTv.setText(String.format(numberIncreaseFormat, data.getConfirmedIncr()));
        curedCountIncreaseTv.setText(String.format(numberIncreaseFormat, data.getCuredIncr()));
        deathCountIncreaseTv.setText(String.format(numberIncreaseFormat, data.getDeadIncr()));

    }

    public void setData(String time, int currentConfirmedCount, int confirmedCount, int curedCount, int deadCount ) {

        String timeFormat = getResources().getString(R.string.latest_time);
        String numberFormat = getResources().getString(R.string.number);
        time = time.substring(0,time.length()-3);
        time = Utils.paserTime(Long.parseLong(time));
        timeTv.setText(String.format(timeFormat, time.substring(0, 10)));

        curConfirmedCountTv.setText(String.format(numberFormat, currentConfirmedCount));
        confirmedCountTv.setText(String.format(numberFormat, confirmedCount));
        curedCountTv.setText(String.format(numberFormat, curedCount));
        deathCountTv.setText(String.format(numberFormat, deadCount));

        curConfirmedCountIncreaseTv.setVisibility(GONE);
        confirmedCountIncreaseTv.setVisibility(GONE);
        curedCountIncreaseTv.setVisibility(GONE);
        deathCountIncreaseTv.setVisibility(GONE);

        curConfirmedCountIncreaseTextTv.setVisibility(GONE);
        confirmedCountIncreaseTextTv.setVisibility(GONE);
        curedCountIncreaseTextTv.setVisibility(GONE);
        deathCountIncreaseTextTv.setVisibility(GONE);
    }

}
