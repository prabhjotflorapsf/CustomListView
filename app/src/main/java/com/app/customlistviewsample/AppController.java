package com.app.customlistviewsample;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    private static final String BASE_URL = "https://api.github.com/";
    public static AppController mInstance;
    private static OkHttpClient okHttpClient = null;

    public static Retrofit getRetroInstance() {

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);


        okHttpClient = null;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
//                    .addInterceptor(logging)
                .dispatcher(dispatcher)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
