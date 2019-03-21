package com.example.im;

import android.app.Application;

public class AppApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getInstance() {
        return application;
    }
}
