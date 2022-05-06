package com.example.retrofitagain2;

import com.example.retrofitagain2.di.components.AppComponent;
import com.example.retrofitagain2.di.components.DaggerAppComponent;
import com.example.retrofitagain2.di.module.AppModule;

public class Application extends android.app.Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .build();
    }
}