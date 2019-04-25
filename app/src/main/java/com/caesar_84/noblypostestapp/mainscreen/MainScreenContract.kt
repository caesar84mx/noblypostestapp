package com.caesar_84.noblypostestapp.mainscreen

import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.IBasePresenter
import com.caesar_84.noblypostestapp.commons.mvpabstracts.view.BaseView
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

interface MainScreenContract {
    interface Presenter: IBasePresenter<View> {
        fun presentScreen()
        fun refreshArticles()
    }
    interface View: BaseView {
        fun showArticles(articles: List<Article>)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showErrorMessage(title: String, message: String)
        fun showListEmpty()
    }
}