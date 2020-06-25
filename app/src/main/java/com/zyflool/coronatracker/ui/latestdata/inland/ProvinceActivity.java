package com.zyflool.coronatracker.ui.latestdata.inland;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.TimeLines;
import com.zyflool.coronatracker.net.AreaResultResponse;
import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.util.DataDisplayView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProvinceActivity extends AppCompatActivity implements ProvinceContract.ProvinceView{

    private String Location, LocationEng;

    private ProvinceContract.ProvincePresenter mPresenter;

    private Toolbar mTb;
    private LineChart mChart;
    private DataDisplayView mDdv;
    private TextView mTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);

        mPresenter = new ProvincePresenter(this, ProvinceRepository.getInstance());

        Location = getIntent().getStringExtra("Location");
        LocationEng = getIntent().getStringExtra("LocationEng");

        mTb = findViewById(R.id.tb_province);
        mChart = findViewById(R.id.lc_pro);
        mDdv = findViewById(R.id.ddv_pro);
        mTv = findViewById(R.id.tv_province);

        mTb.setTitle(Location);
        mTb.setTitleTextColor(Color.parseColor("#ffffff"));
        mTb.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mTb.setNavigationOnClickListener(v -> finish());
        if ( LocationEng.length() == 0 ) {
            mChart.setVisibility(View.GONE);
            mTv.setVisibility(View.VISIBLE);
        } else {
            mChart.setVisibility(View.VISIBLE);
            mTv.setVisibility(View.GONE);
        }

        initData();

        mPresenter.getData(LocationEng);

    }

    private void initData() {

        NetUtil.getInstance().getApi().getArea("", Location)
                .retry(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AreaResultResponse areaResultResponse) {
                        Log.e("ProvinceActivity","province data Success");
                        mDdv.setData(areaResultResponse.getResults().get(0).getUpdateTime()+"",
                                areaResultResponse.getResults().get(0).getCurrentConfirmedCount(),
                                areaResultResponse.getResults().get(0).getConfirmedCount(),
                                areaResultResponse.getResults().get(0).getCuredCount(),
                                areaResultResponse.getResults().get(0).getDeadCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("ProvinceActivity","province data Fail");
                        showError("获取数据失败，请检查网络或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setChart(List<List<TimeLines>> dataList) {

        List<TimeLines> confirmedTimeline = dataList.get(0);
        List<TimeLines> deathTimeline = dataList.get(1);

        List<Entry> confirmedCount = new ArrayList<>();
        List<Entry> deathCount = new ArrayList<>();

        for (int i = 0 ; i < confirmedTimeline.size() ;i++ )
            confirmedCount.add(new Entry((float) i, (float)confirmedTimeline.get(i).getNum()));
        for (int i = 0 ; i< deathTimeline.size() ;i++ )
            deathCount.add(new Entry((float)i, (float)deathTimeline.get(i).getNum()));

        LineDataSet confirmedCountSet = new LineDataSet(confirmedCount, "累计确诊人数");
        LineDataSet deathCountSet = new LineDataSet(deathCount, "累计死亡人数");

        confirmedCountSet.setColor(Color.parseColor("#620F0F"));
        confirmedCountSet.setCircleColor(Color.parseColor("#620F0F"));
        confirmedCountSet.setCircleColors(Color.parseColor("#620F0F"));
        confirmedCountSet.setValueTextColor(Color.parseColor("#620F0F"));
        confirmedCountSet.setColors(Color.parseColor("#620F0F"));

        confirmedCountSet.setDrawCircles(false);

        deathCountSet.setDrawCircles(false);
        deathCountSet.setColors(Color.parseColor("#000000"));
        deathCountSet.setColor(Color.parseColor("#000000"));
        deathCountSet.setCircleColor(Color.parseColor("#000000"));
        deathCountSet.setCircleColors(Color.parseColor("#000000"));
        deathCountSet.setValueTextColor(Color.parseColor("#000000"));

        Description description = mChart.getDescription();
        description.setText("历史数据折线图"); // 设置右下角备注

        LineData data = new LineData(confirmedCountSet);
        data.addDataSet(deathCountSet);
        mChart.setData(data);
        mChart.invalidate(); // 刷新


        mChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return confirmedTimeline.get((int)value).getTime();
            }
        });

        mChart.invalidate();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
