package com.gis.daggerdemo;

import android.app.Application;

import com.gis.daggerdemo.di.AppComponent;
import com.gis.daggerdemo.di.DaggerAppComponent;

public class BaseApplication extends Application {
    private AppComponent appComponent;
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        appComponent= DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
    public static BaseApplication getContext() {
        return mContext;
    }
}
