package com.caesar_84.noblypostestapp.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class NoblyPosTestAppBaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    protected abstract fun init(savedInstanceState: Bundle?)
}