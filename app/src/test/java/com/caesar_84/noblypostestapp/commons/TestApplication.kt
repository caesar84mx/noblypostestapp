package com.caesar_84.noblypostestapp.commons

import android.app.Application
import com.caesar_84.noblypostestapp.commons.di.DaggerTestComponent
import com.caesar_84.noblypostestapp.commons.di.TestComponent
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.di.DataAccessModule

class TestApplication: Application() {
    lateinit var injector: TestComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        injector = DaggerTestComponent.builder()
                .dataAccessModule(DataAccessModule(this))
                .build()
    }

    companion object {
        private var INSTANCE: TestApplication? = null

        @JvmStatic
        fun getInstance(): TestApplication = INSTANCE!!
    }
}