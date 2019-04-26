package com.caesar_84.noblypostestapp.mainscreen.ui

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.View
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.NoblyPosTestAppBaseActivity
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreen : NoblyPosTestAppBaseActivity(), MainScreenContract.View {
    protected lateinit var mPresenter: MainScreenPresenter

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main_screen)
        mPresenter.attachView(this)
        mPresenter.presentScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
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
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun showListEmpty() {
        rvArticlesList.visibility = View.GONE
        tvNoArticles.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
