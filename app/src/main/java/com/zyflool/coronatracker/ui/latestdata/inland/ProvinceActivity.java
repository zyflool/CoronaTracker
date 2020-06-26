package com.zyflool.coronatracker.ui.latestdata.inland;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;
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
    private SpinKitView mSkv;

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
        mSkv = findViewById(R.id.skv_province);
        mSkv.setVisibility(View.VISIBLE);
        Style style = Style.values()[9];
        Sprite drawable = SpriteFactory.create(style);
        mSkv.setIndeterminateDrawable(drawable);
        mSkv.setColor(getResources().getColor(android.R.color.black, null));

        mDdv.setFabListener(v -> initData());

        mTb.setTitle(Location);
        mTb.setNavigationOnClickListener(v -> finish());

        //刚开始没有数据 不显示图表
        mChart.setVisibility(View.INVISIBLE);

        if ( LocationEng.length() == 0 ) {
            mTv.setVisibility(View.VISIBLE);
            mTv.setText(R.string.no_detail_data);
            mSkv.setVisibility(View.GONE);
        } else {
            mTv.setVisibility(View.VISIBLE);
            mTv.setText(R.string.loading_data);
            mPresenter.getData(LocationEng);
        }

        initData();

    }

    //获取整体数据
    private void initData() {
        NetUtil.getInstance().getApi().getArea("", Location, "currentConfirmedCount")
                .retry(5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AreaResultResponse areaResultResponse) {
                        Log.e("ProvinceActivity","province data Success");
                        mDdv.setData(areaResultResponse.getResults().get(0).getCurrentConfirmedCount(),
                                areaResultResponse.getResults().get(0).getConfirmedCount(),
                                areaResultResponse.getResults().get(0).getCuredCount(),
                                areaResultResponse.getResults().get(0).getDeadCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("ProvinceActivity","province data Fail");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //设置图表
    @Override
    public void setChart(List<List<TimeLines>> dataList) {
        Log.e("ProvinceActivity", "setChart");

        //请求成功，文字消失，显示图表
        mChart.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.GONE);
        mSkv.setVisibility(View.GONE);

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

        confirmedCountSet.setColor(getResources().getColor(R.color.confirmedTextColor, null));
        confirmedCountSet.setCircleColor(getResources().getColor(R.color.confirmedTextColor, null));
        confirmedCountSet.setCircleColors(getResources().getColor(R.color.confirmedTextColor, null));
        confirmedCountSet.setValueTextColor(getResources().getColor(R.color.confirmedTextColor, null));
        confirmedCountSet.setColors(getResources().getColor(R.color.confirmedTextColor, null));

        confirmedCountSet.setDrawCircles(false);

        deathCountSet.setDrawCircles(false);
        deathCountSet.setColors(getResources().getColor(R.color.deathTextColor, null));
        deathCountSet.setColor(getResources().getColor(R.color.deathTextColor, null));
        deathCountSet.setCircleColor(getResources().getColor(R.color.deathTextColor, null));
        deathCountSet.setCircleColors(getResources().getColor(R.color.deathTextColor, null));
        deathCountSet.setValueTextColor(getResources().getColor(R.color.deathTextColor, null));

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
        mTv.setText(R.string.load_data_fail);
        mTv.setClickable(true);
        mTv.setOnClickListener(v -> {
            mPresenter.getData(LocationEng);
            mTv.setClickable(false);
            mTv.setOnClickListener(null);
            mTv.setText(R.string.loading_data);
            mSkv.setVisibility(View.VISIBLE);
        });
    }

}
