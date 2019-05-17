package com.caesar_84.noblypostestapp.commons

import android.app.Application
import com.caesar_84.noblypostestapp.commons.di.AppComponent
import com.caesar_84.noblypostestapp.commons.di.DaggerAppComponent
import com.caesar_84.noblypostestapp.commons.di.MainScreenModule
import com.caesar_84.noblypostestapp.mainscreen.di.DataAccessModule

class NoblyPosTestApplication : Application() {
    lateinit var injector: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        injector = DaggerAppComponent.builder()
            .dataAccessModule(DataAccessModule(this))
            .mainScreenModule(MainScreenModule())
            .build()
    }

    companion object {
        private var INSTANCE: NoblyPosTestApplication? = null

        @JvmStatic
        fun getInstance(): NoblyPosTestApplication = INSTANCE!!
    }
}