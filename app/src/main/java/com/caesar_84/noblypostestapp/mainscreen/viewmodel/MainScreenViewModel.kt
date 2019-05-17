package com.caesar_84.noblypostestapp.mainscreen.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

class MainScreenViewModel: ViewModel() {
    val articleItems: MutableLiveData<List<Article>> by lazy { MutableLiveData<List<Article>>() }
}