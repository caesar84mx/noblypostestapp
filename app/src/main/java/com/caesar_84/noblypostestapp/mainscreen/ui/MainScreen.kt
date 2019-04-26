package com.caesar_84.noblypostestapp.mainscreen.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.View
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.NoblyPosTestAppBaseActivity
import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.activity_main_screen.*
import javax.inject.Inject

class MainScreen : NoblyPosTestAppBaseActivity(), MainScreenContract.View {
    @Inject
    protected lateinit var mPresenter: MainScreenPresenter

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main_screen)
        NoblyPosTestApplication.getInstance().injector.inject(this)
        mPresenter.attachView(this)
        mPresenter.presentScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun showLoadingIndicator() {
        runOnUiThread { flSpinner.visibility = View.VISIBLE }
    }

    override fun hideLoadingIndicator() {
        runOnUiThread { flSpinner.visibility = View.VISIBLE }
    }

    override fun showArticles(articles: List<Article>) {
        //TODO: implement
        rvArticlesList.visibility = View.VISIBLE
    }

    override fun showErrorMessage(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun showListEmpty(isToBeShown: Boolean) {
        rvArticlesList.visibility = View.GONE
        tvNoArticles.visibility = if(isToBeShown) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
