package com.zyflool.coronatracker.ui.latestdata.inland;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.data.CoronaData;
import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.OverallResultResponse;
import com.zyflool.coronatracker.ui.info.InfoFragment;
import com.zyflool.coronatracker.ui.latestdata.sortrecyclerview.PinyinComparator;
import com.zyflool.coronatracker.ui.latestdata.sortrecyclerview.SideBar;
import com.zyflool.coronatracker.ui.latestdata.sortrecyclerview.SortAdapter;
import com.zyflool.coronatracker.ui.latestdata.sortrecyclerview.SortModel;
import com.zyflool.coronatracker.util.Constants;
import com.zyflool.coronatracker.util.DataDisplayView;
import com.zyflool.coronatracker.util.SPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InlandFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SideBar sideBar;
    private TextView dialog;
    private DataDisplayView mDdv;
    private FloatingActionButton mFab;

    private SPUtils spUtils;

    private LinearLayoutManager manager;

    private SortAdapter adapter;
    private List<SortModel> sourceDataList;

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator pinyinComparator;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = SPUtils.getInstance("OverAll");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inland, container, false);
        initViews(view);
        initData();

        return view;
    }


    private void initViews(View v) {

        pinyinComparator = new PinyinComparator();

        sideBar = v.findViewById(R.id.sb_inland);
        dialog = v.findViewById(R.id.tv_inland_dialog);
        mDdv = v.findViewById(R.id.ddv_inland);
        mFab = v.findViewById(R.id.fab_inland);
        mFab.setOnClickListener(view -> getRemoteOverall());
        sideBar.setTextView(dialog);

        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));

                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        mRecyclerView =  v.findViewById(R.id.rv_inland);
        sourceDataList = filledData();

        // 根据a-z进行排序源数据
        Collections.sort(sourceDataList, pinyinComparator);


        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        adapter = new SortAdapter(getContext(), sourceDataList);
        mRecyclerView.setAdapter(adapter);

        //item点击事件
        adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProvinceActivity.class);
                intent.putExtra("Location", adapter.getItem(position).getName());
                intent.putExtra("LocationEng", adapter.getItem(position).getNameEng());
                startActivity(intent);
            }
        });

    }

    /**
     * 为RecyclerView填充数据
     */
    private List<SortModel> filledData() {
        List<SortModel> mSortList = new ArrayList<>();

        for (int i = 0; i < Constants.ProvinceName.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(Constants.ProvinceName[i]);
            sortModel.setNameEng(Constants.ProvinceNameEng[i]);

            String pinyin = Constants.ProvinceNamePinyin[i];
            String sortString = pinyin.substring(0, 1).toUpperCase();
            sortModel.setLetters(sortString.toUpperCase());

            mSortList.add(sortModel);
        }
        return mSortList;
    }


    private void initData() {
        if ( spUtils.getString("updateTime","").length() == 0 )
            getRemoteOverall();
        else
            mDdv.setData(
                    new CoronaData(spUtils.getString("updateTime"),
                            spUtils.getInt("InlandCurrentConfirmedCount"),
                            spUtils.getInt("InlandConfirmedCount"),
                            spUtils.getInt("InlandCuredCount"),
                            spUtils.getInt("InlandDeadCount"),
                            spUtils.getInt("InlandCurrentConfirmedIncr"),
                            spUtils.getInt("InlandConfirmedIncr"),
                            spUtils.getInt("InlandCuredIncr"),
                            spUtils.getInt("InlandDeadIncr")
                    ));
    }

    private void getRemoteOverall() {
        NetUtil.getInstance().getApi().getOverAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OverallResultResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OverallResultResponse overallResultResponse) {
                        Log.e("InlandFragment", "get remote Overall Success");
                        OverallResultResponse.ResultsBean t = overallResultResponse.getResults().get(0);

                        spUtils.put("updateTime", t.getUpdateTime()+"");

                        spUtils.put("InlandCurrentConfirmedCount", t.getCurrentConfirmedCount());
                        spUtils.put("InlandConfirmedCount", t.getConfirmedCount());
                        spUtils.put("InlandCuredCount",t.getCuredCount());
                        spUtils.put("InlandDeadCount", t.getDeadCount());

                        spUtils.put("InlandCurrentConfirmedIncr", t.getCurrentConfirmedIncr());
                        spUtils.put("InlandConfirmedIncr", t.getConfirmedIncr());
                        spUtils.put("InlandCuredIncr", t.getCuredIncr());
                        spUtils.put("InlandDeadIncr", t.getDeadIncr());

                        mDdv.setData(new CoronaData(t.getUpdateTime()+"",
                                t.getCurrentConfirmedCount(), t.getConfirmedCount(),
                                t.getCuredCount(), t.getDeadCount(),
                                t.getCurrentConfirmedIncr(), t.getConfirmedIncr(),
                                t.getCuredIncr(), t.getDeadIncr()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("inlandFragment","data fail");
                        showError("获取数据失败，请检查网络或稍后重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

}