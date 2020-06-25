package com.zyflool.coronatracker.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {

    private OkHttpClient client;
    private Service api1, api2;
    private Gson gson;

    private NetUtil() {
        gson=new Gson();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        client= new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        api1 = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://lab.isaaclin.cn/nCoV/")
                .build()
                .create(Service.class);

        api2 = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://coronavirus-tracker-api.herokuapp.com/")
                .build()
                .create(Service.class);
    }

    public Gson getGson(){
        return gson;
    }
    public static NetUtil getInstance() {
        return NetUtilHolder.INSTANCE;
    }

    private static class NetUtilHolder {
        private static NetUtil INSTANCE = new NetUtil();

    }

    public OkHttpClient getClient() {
        return client;
    }

    public Service getApi() {
        return api1;
    }

    public Service getApi2 () {
        return api2;
    }
}
