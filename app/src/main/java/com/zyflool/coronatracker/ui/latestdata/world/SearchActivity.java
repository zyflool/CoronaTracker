package com.zyflool.coronatracker.ui.latestdata.world;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zyflool.coronatracker.R;
import com.zyflool.coronatracker.net.AreaResultResponse;
import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.util.AppExecutors;
import com.zyflool.coronatracker.util.DataDisplayView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    private SearchView mSv;
    private RecyclerView mRv;
    private SearchAdapter mAdapter;
    private DataDisplayView mDdv;

    private CountryLocalDataSource mCouDataSource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mCouDataSource = CountryLocalDataSource.getInstance
                (CountryDataBase.getInstance(this).CountryDao(), new AppExecutors());

        mSv = findViewById(R.id.sv_search);
        mRv = findViewById(R.id.rv_search);
        mDdv = findViewById(R.id.ddv_search);

        mDdv.setFabListener(v -> getData(mSv.getQuery().toString()));

        mDdv.setVisibility(View.GONE);

        mAdapter = new SearchAdapter(new ArrayList<>());
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                setDdv(false, "");
                    mCouDataSource.searchCountry(newText)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<List<Country>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(List<Country> countries) {
                                    Log.e("SearchActivity", "query text Success");
                                    List<String> result = new ArrayList<>();
                                    for (int i = 0; i < countries.size(); i++)
                                        result.add(countries.get(i).getName());
                                    mAdapter.setData(result);
                                    if (result.size() == 1) {
                                        setDdv(true, result.get(0));
                                    } else setDdv(false, "");

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    Log.e("SearchActivity", "query text Fail");
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                return false;
            }
        });
    }

    public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

        private List<String> mResultList;

        public SearchAdapter(List<String> resultList) {
            mResultList = resultList;
        }

        public void setData(List<String> list) {
            mResultList.clear();
            mResultList.addAll(list);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(getBaseContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
            holder.textView.setText(mResultList.get(position));
            holder.itemView.setOnClickListener(v -> {
                mSv.setQuery(mResultList.get(position), false);
                setDdv(true, mResultList.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return mResultList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }

    private void setDdv (boolean ifVis, String result) {
        if ( ifVis )
            getData(result);
        else
            mDdv.setVisibility(View.GONE);
    }

    private void getData(String result) {
        NetUtil.getInstance().getApi().getArea(result, "", "currentConfirmedCount")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(AreaResultResponse::getResults)
                .subscribe(new Observer<List<AreaResultResponse.ResultsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<AreaResultResponse.ResultsBean> resultsBeans) {
                        mDdv.setData(resultsBeans.get(0).getCurrentConfirmedCount(),
                                resultsBeans.get(0).getConfirmedCount(),
                                resultsBeans.get(0).getCuredCount(),
                                resultsBeans.get(0).getDeadCount());
                        Log.e("SearchActivity", resultsBeans.get(0).getCountryName()+"  "
                                +resultsBeans.get(0).getProvinceName()+" "+
                                resultsBeans.get(0).getCurrentConfirmedCount()+" "+
                                resultsBeans.get(0).getConfirmedCount()+" "+
                                resultsBeans.get(0).getCuredCount()+" "+
                                resultsBeans.get(0).getDeadCount());
                        mDdv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDdv.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                        Log.e("SearchActivity", "get Remote Area Data Fail");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
