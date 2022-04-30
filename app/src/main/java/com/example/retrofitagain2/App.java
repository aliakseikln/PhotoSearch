package com.example.retrofitagain2;

import android.app.Application;

import com.example.retrofitagain2.di.components.DaggerAppComponent;
import com.example.retrofitagain2.di.module.AppModule;

public class App extends Application {

    private static DaggerAppComponent appComponent;

    //  private static Application instance;
    //  public static Context getContext() {
//        return instance.getApplicationContext();
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        // instance = this;
        appComponent = (DaggerAppComponent) DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public DaggerAppComponent getAppComponent() {
        return appComponent;
    }
}
