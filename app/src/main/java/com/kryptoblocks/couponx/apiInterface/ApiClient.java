package com.kryptoblocks.couponx.apiInterface;

//import com.squareup.okhttp.OkHttpClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;
    public static final String Base = "https://couponx.rewardx.io:8443/";




    public static Retrofit getClient() {

        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        okhttp.addInterceptor(httpLoggingInterceptor);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base)
                    .client(okhttp.build())
                    //.setLogLevel(RestAdapter.LogLevel.FULL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
