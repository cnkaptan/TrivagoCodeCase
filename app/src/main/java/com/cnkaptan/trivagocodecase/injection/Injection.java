package com.cnkaptan.trivagocodecase.injection;

import com.cnkaptan.trivagocodecase.data.remote.TrackApi;
import com.cnkaptan.trivagocodecase.data.Repository;
import com.cnkaptan.trivagocodecase.data.RepositoryImpl;

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
public class Injection {

    private static final String BASE_URL = TrackApi.BASE_URL;
    private static OkHttpClient okHttpClient;
    private static TrackApi trackApi;
    private static Retrofit retrofitInstance;

    public static void init() {

    }

    public static Repository provideTrackTvRepo() {
        return new RepositoryImpl(provideTrackTvService());
    }

    public static TrackApi provideTrackTvService() {
        if (trackApi == null) {
            trackApi = getRetrofitInstance().create(TrackApi.class);
        }
        return trackApi;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(logging);
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("trakt-api-key","ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086")
                            .addHeader("trakt-api-version", "2")
                            .addHeader("Content-type","application/json");

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            okHttpClient = builder.build();
        }

        return okHttpClient;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .client(Injection.getOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }
}
