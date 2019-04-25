package com.caesar_84.noblypostestapp.commons

import android.app.Application
import com.caesar_84.noblypostestapp.commons.di.AppComponent

class NoblyPosTestApplication: Application() {
    private lateinit var mInjector: AppComponent

    override fun onCreate() {
        super.onCreate()
        INJECTOR = mInjector
    }

    companion object {
        private lateinit var INJECTOR: AppComponent

        @JvmStatic
        fun getInjector(): AppComponent = INJECTOR
    }
}