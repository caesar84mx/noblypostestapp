package com.caesar_84.noblypostestapp.commons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class NoblyPosTestAppBaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    protected abstract fun init(savedInstanceState: Bundle?)
}