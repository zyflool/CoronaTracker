package com.zyflool.coronatracker.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.CoronaData;

/**
 * 国内数据展示框
 */
public class InlandDataDisplayView extends LinearLayout {

    private TextView curConfirmedCountTv;
    private TextView confirmedCountTv;
    private TextView curedCountTv;
    private TextView deathCountTv;
    private TextView importedCountTv;
    private TextView asymptomaticCountTv;
    private TextView curConfirmedCountIncreaseTv;
    private TextView confirmedCountIncreaseTv;
    private TextView curedCountIncreaseTv;
    private TextView deathCountIncreaseTv;
    private TextView importedCountIncreaseTv;
    private TextView asymptomaticCountIncreaseTv;

    public InlandDataDisplayView(Context context) {
        super(context);
        initView(context);
    }

    public InlandDataDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public InlandDataDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public InlandDataDisplayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_data_display_inland, this, true);
        curConfirmedCountTv = findViewById(R.id.tv_cur_confirmed_count_number);
        curedCountTv = findViewById(R.id.tv_cured_count_number);
        confirmedCountTv = findViewById(R.id.tv_confirmed_count_number);
        deathCountTv = findViewById(R.id.tv_death_count_number);
        importedCountTv = findViewById(R.id.tv_imported_count_number);
        asymptomaticCountTv = findViewById(R.id.tv_asymptomatic_count_number);

        curConfirmedCountIncreaseTv = findViewById(R.id.tv_cur_confirmed_count_increase_number);
        curedCountIncreaseTv = findViewById(R.id.tv_cured_count_increase_number);
        confirmedCountIncreaseTv = findViewById(R.id.tv_confirmed_count_increase_number);
        deathCountIncreaseTv = findViewById(R.id.tv_death_count_increase_number);
        importedCountIncreaseTv = findViewById(R.id.tv_imported_count_increase_number);
        asymptomaticCountIncreaseTv = findViewById(R.id.tv_asymptomatic_count_increase_number);
    }

    public void setData(CoronaData data) {

        String numberFormat = getResources().getString(R.string.number);

        curConfirmedCountTv.setText(String.format(numberFormat, data.getCurrentConfirmedCount()));
        confirmedCountTv.setText(String.format(numberFormat, data.getConfirmedCount()));
        curedCountTv.setText(String.format(numberFormat, data.getCuredCount()));
        deathCountTv.setText(String.format(numberFormat, data.getDeadCount()));
        importedCountTv.setText(String.format(numberFormat, data.getImportedCount()));
        asymptomaticCountTv.setText(String.format(numberFormat, data.getAsymptomaticCount()));

        setIncreaseText(curConfirmedCountIncreaseTv, data.getCurrentConfirmedIncr());
        setIncreaseText(confirmedCountIncreaseTv, data.getConfirmedIncr());
        setIncreaseText(curedCountIncreaseTv, data.getCuredIncr());
        setIncreaseText(deathCountIncreaseTv, data.getDeadIncr());
        setIncreaseText(importedCountIncreaseTv, data.getImportedIncr());
        setIncreaseText(asymptomaticCountIncreaseTv, data.getAsymptomaticIncr());

    }

    private void setIncreaseText(TextView Tv, int incr) {
        String numberIncr = getResources().getString(R.string.increase_number);
        String numberDecr = getResources().getString(R.string.decrease_number);
        if ( incr >= 0 )
            Tv.setText(String.format(numberIncr,incr));
        else
            Tv.setText(String.format(numberDecr,incr));

    }

}
