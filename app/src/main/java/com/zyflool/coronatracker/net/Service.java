package com.zyflool.coronatracker.net;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * https://lab.isaaclin.cn/nCoV/generator#/
 * https://coronavirus-tracker-api.herokuapp.com/#/
 */
public interface Service {

    @GET("api/overall")
    Observable<OverallResultResponse> getOverAll();

    @GET("api/area")
    Observable<AreaResultResponse> getArea(@Query("country") String country, @Query("province") String province);

    //province 为空
    @GET("api/news")
    Observable<NewsResultResponse> getNews(@Query("province") String province, @Query("page") int page, @Query("num") int num);

    //rumorType = 1
    @GET("api/rumors")
    Observable<RumorsResultResponse> getRumors(@Query("rumorType") int rumorType, @Query("page") int page, @Query("num") int num);

    @GET("api/provinceName")
    Observable<ProvinceNameResponse> getProvinceName(@Query("lang") String lang);

    //source = jhu, timelines = true
    @GET("v2/locations")
    Observable<ResponseBody> getLocation(@Query("source") String source, @Query("province") String province, @Query("timelines") boolean timelines);

}
