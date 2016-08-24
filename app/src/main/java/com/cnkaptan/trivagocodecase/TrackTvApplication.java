package com.cnkaptan.trivagocodecase;

import android.app.Application;

import com.cnkaptan.trivagocodecase.injection.AppComponent;
import com.cnkaptan.trivagocodecase.injection.DaggerAppComponent;

/**
 * Created by cnkaptan on 20/08/16.
 */
public class TrackTvApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.create();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
