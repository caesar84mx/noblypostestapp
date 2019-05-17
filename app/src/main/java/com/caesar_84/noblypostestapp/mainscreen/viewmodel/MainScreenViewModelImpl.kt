package com.caesar_84.noblypostestapp.mainscreen.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

class MainScreenViewModelImpl : MainScreenViewModel() {
    override fun onRefreshMenuItemClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val articleItems: MutableLiveData<List<Article>> by lazy { MutableLiveData<List<Article>>() }
}