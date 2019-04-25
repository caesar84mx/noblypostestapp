package com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter

import com.caesar_84.noblypostestapp.commons.mvpabstracts.view.BaseView

open class BasePresenter<T : BaseView> : IBasePresenter<T> {
    protected var view: T? = null
        private set

    override var isViewAttached: Boolean = view != null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }




}