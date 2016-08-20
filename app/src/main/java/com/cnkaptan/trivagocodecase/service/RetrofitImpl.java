package com.cnkaptan.trivagocodecase.service;

import com.cnkaptan.trivagocodecase.BuildConfig;
import com.cnkaptan.trivagocodecase.TrackApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class RetrofitImpl {
    private static final String BASE_URL = TrackApi.BASE_URL;
    private TrackApi trackApi;

    public RetrofitImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideLoggingCapableHttpClient())
                .build();

        trackApi = retrofit.create(TrackApi.class);
    }

    private OkHttpClient provideLoggingCapableHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(logging);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("trakt-api-key","ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                        .addHeader("trakt-api-version", "2")
                        .addHeader("Content-type","application/json");
                        // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    public TrackApi getTrackApi(){
        return trackApi;
    }
}