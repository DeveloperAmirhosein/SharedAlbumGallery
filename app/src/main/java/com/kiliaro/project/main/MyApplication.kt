package com.kiliaro.project.main

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: MyApplication
            private set

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }


}