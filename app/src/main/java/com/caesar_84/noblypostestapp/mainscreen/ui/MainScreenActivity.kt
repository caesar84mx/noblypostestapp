package com.caesar_84.noblypostestapp.mainscreen.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.NoblyPosTestAppBaseActivity
import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.activity_main_screen.*
import javax.inject.Inject

class MainScreenActivity : NoblyPosTestAppBaseActivity(), MainScreenContract.View {
    @Inject
    protected lateinit var mPresenter: MainScreenPresenter

    private var articlesInfoAdapter: ArticlesInfoRvAdapter? = null

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.miRefresh -> {
                mPresenter.refreshArticles()
                true
            }

            else -> false
        }
    }

    override fun getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun showLoading(isToBeShown: Boolean) {
        runOnUiThread {
            flSpinner.visibility = if (isToBeShown) View.VISIBLE else View.GONE
        }
    }

    override fun initArticlesInfoList() {
        val viewManager = LinearLayoutManager(this)
        articlesInfoAdapter = ArticlesInfoRvAdapter()

        rvArticlesList.apply {
            layoutManager = viewManager
            adapter = articlesInfoAdapter
        }
    }

    override fun showArticles(articles: List<Article>) {
        rvArticlesList.visibility = View.VISIBLE
        articlesInfoAdapter?.setArticlesInfoList(articles)
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
        tvNoArticles.visibility = if (isToBeShown) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun getNoConnectionDialogTitle(): String {
        return getString(R.string.no_connection_dialog_title)
    }

    override fun getNoConnectionDialogMessage(): String {
        return getString(R.string.no_connection_dialog_message)
    }
}
