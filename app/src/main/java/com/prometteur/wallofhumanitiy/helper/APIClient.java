package com.prometteur.wallofhumanitiy.helper;

import com.prometteur.wallofhumanitiy.Utility.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prometteur on 4/4/18.
 */

public class APIClient {

    private static int time = 60;
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(time, TimeUnit.SECONDS)
                .writeTimeout(time, TimeUnit.SECONDS)
                .readTimeout(time, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)  //  http://nicu.rebelute.in/        //    https://reqres.in
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
