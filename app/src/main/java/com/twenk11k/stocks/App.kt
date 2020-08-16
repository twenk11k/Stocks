package com.twenk11k.stocks

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: MultiDexApplication() {

    companion object {

        lateinit var application: Application

        fun getContext(): Context {
            return application.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}