package com.caesar_84.noblypostestapp.mainscreen.ui

import android.os.Bundle
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.NoblyPosTestAppBaseActivity
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter

class MainScreen : NoblyPosTestAppBaseActivity(), MainScreenContract.View {
    lateinit var mPresenter: MainScreenPresenter

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main_screen)
        mPresenter.attachView(this)
        mPresenter.presentScreen()
    }

    override fun showLoadingIndicator() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingIndicator() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showArticles(articles: List<Article>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(title: String, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showListEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
