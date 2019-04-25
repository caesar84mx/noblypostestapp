package com.caesar_84.noblypostestapp.commons

import android.app.Application
import com.caesar_84.noblypostestapp.commons.di.AppComponent
import com.caesar_84.noblypostestapp.commons.di.DaggerAppComponent
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.di.DataAccessModule

class NoblyPosTestApplication : Application() {
    lateinit var injector: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppComponent.builder()
                .dataAccessModule(DataAccessModule(this))
                .build()

        INSTANCE = this
    }

    companion object {
        private lateinit var INSTANCE: NoblyPosTestApplication

        @JvmStatic
        fun getInstance(): NoblyPosTestApplication = INSTANCE
    }
}