package com.zyflool.coronatracker.ui.latestdata.world;

import com.zyflool.coronatracker.net.NetUtil;
import com.zyflool.coronatracker.net.ProvinceNameResponse;
import com.zyflool.coronatracker.util.AppExecutors;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CountryLocalDataSource {

    private static CountryLocalDataSource INSTANCE;

    private CountryDao mCountryDao;

    private AppExecutors mAppExecutors;

    public static CountryLocalDataSource getInstance(CountryDao countryDao, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (CountryLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CountryLocalDataSource(countryDao, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    private CountryLocalDataSource(CountryDao countryDao, AppExecutors appExecutors) {
        this.mAppExecutors = appExecutors;
        this.mCountryDao = countryDao;

    }

    public void insertCountry(String data) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCountryDao.insertCountry(new Country(data));
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    public Observable<List<Country>> searchCountry(String s) {
        return Observable.create(new ObservableOnSubscribe<List<Country>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Country>> emitter) throws Exception {
                emitter.onNext(mCountryDao.searchCountry(s));
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<List<String>> getProvinceName() {
        return NetUtil.getInstance().getApi().getProvinceName("zh")
                .subscribeOn(Schedulers.io())
                .map(provinceNameResponse -> provinceNameResponse.getResults());
    }

}
