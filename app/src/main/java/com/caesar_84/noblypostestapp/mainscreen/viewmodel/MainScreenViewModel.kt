package com.caesar_84.noblypostestapp.mainscreen.viewmodel


import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

abstract class MainScreenViewModel: ViewModel() {
    abstract val articleItems: MediatorLiveData<List<Article>>

    abstract fun onRefreshMenuItemClicked()
}