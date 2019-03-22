package com.example.im

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    companion object {
        var instance: Application? = null
            private set
    }
}
