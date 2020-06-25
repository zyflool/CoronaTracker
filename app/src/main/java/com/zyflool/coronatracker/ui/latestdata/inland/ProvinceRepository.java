package com.zyflool.coronatracker.ui.latestdata.inland;


import com.zyflool.coronatracker.net.NetUtil;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ProvinceRepository {

    private static ProvinceRepository INSTANCE;

    public static ProvinceRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ProvinceRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new ProvinceRepository();
                }
            }
        }
        return INSTANCE;
    }

    private ProvinceRepository() {}

    public Observable<ResponseBody> getLocationData(String source, String LocationEng, boolean timelines) {
        return NetUtil.getInstance().getApi2().getLocation(source,LocationEng, timelines)
                .subscribeOn(Schedulers.io());
    }


}
