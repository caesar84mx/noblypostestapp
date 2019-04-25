package com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter

import com.caesar_84.noblypostestapp.commons.mvpabstracts.view.BaseView

interface BaseMvpPresenter<T: BaseView> {
    var isViewAttached: Boolean
    fun attachView(view: T)
    fun detachView()
}