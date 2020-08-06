package com.gis.daggerdemo.di;

import com.gis.daggerdemo.MainActivity;
import com.gis.daggerdemo.di.module.ContextModule;
import com.gis.daggerdemo.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
