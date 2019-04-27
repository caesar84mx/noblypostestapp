package com.caesar_84.noblypostestapp.mainscreen

import android.net.ConnectivityManager
import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.BaseMvpPresenter
import com.caesar_84.noblypostestapp.commons.mvpabstracts.view.BaseView
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

interface MainScreenContract {
    interface Presenter: BaseMvpPresenter<View> {
        fun presentScreen()
        fun refreshArticles()
    }
    interface View: BaseView {
        fun showArticles(articles: List<Article>)
        fun showLoading(isToBeShown: Boolean)
        fun showErrorMessage(title: String, message: String)
        fun showListEmpty(isToBeShown: Boolean)
        fun getConnectivityManager(): ConnectivityManager
        fun initArticlesInfoList()
        fun getNoConnectionDialogTitle(): String
        fun getNoConnectionDialogMessage(): String
    }
}