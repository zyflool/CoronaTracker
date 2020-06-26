package com.zyflool.coronatracker.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zyflool.coronatracker.data.CoronaData;
import com.zyflool.coronatracker.R;

/**
 * 普通数据显示框（国际、国内各省市）
 */
public class DataDisplayView extends LinearLayout {

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
    private FloatingActionButton mFab;

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
        LayoutInflater.from(context).inflate(R.layout.view_data_display, this, true);
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
        mFab = findViewById(R.id.fab_data_display);
    }

    public void setFabListener(OnClickListener onClickListener) {
        mFab.setOnClickListener(onClickListener);
    }

    public void setData(CoronaData data) {

        String numberFormat = getResources().getString(R.string.number);

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

        setIncreaseText(curConfirmedCountIncreaseTv, data.getCurrentConfirmedIncr());
        setIncreaseText(confirmedCountIncreaseTv, data.getConfirmedIncr());
        setIncreaseText(curedCountIncreaseTv, data.getCuredIncr());
        setIncreaseText(deathCountIncreaseTv, data.getDeadIncr());

    }

    private void setIncreaseText(TextView Tv, int incr) {
        String numberIncr = getResources().getString(R.string.increase_number);
        String numberDecr = getResources().getString(R.string.decrease_number);
        if ( incr >= 0 )
            Tv.setText(String.format(numberIncr,incr));
        else
            Tv.setText(String.format(numberDecr,incr));

    }

    public void setData(int currentConfirmedCount, int confirmedCount, int curedCount, int deadCount ) {

        String numberFormat = getResources().getString(R.string.number);

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
