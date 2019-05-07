package com.caesar_84.noblypostestapp.mainscreen.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.NoblyPosTestAppBaseActivity
import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.commons.utils.Constants.Configuration.MainScreen.ANSWER_KEY
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import kotlinx.android.synthetic.main.activity_main_screen.*
import javax.inject.Inject

class MainScreenActivity : NoblyPosTestAppBaseActivity(), MainScreenContract.View {
    private val nope = "nope"
    private val like = "like"

    private var mIsNotDialogShowed = true

    @Inject
    protected lateinit var mPresenter: MainScreenPresenter

    private var mArticlesInfoAdapter: ArticlesInfoRvAdapter? = null
    private var mMenu: Menu? = null

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main_screen)

        NoblyPosTestApplication.getInstance().injector.inject(this)

        mPresenter.attachView(this)
        mPresenter.presentScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mMenu = menu
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

    override fun onBackPressed() {
        if (isHiremeDialogApplicable()) {
            showRatemeDialog()
        } else {
            super.onBackPressed()
        }
    }

    private fun showRatemeDialog() {
        val dialog = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.rateme_dialog, null)

        dialog.apply {
            setView(view)
            setTitle(getString(R.string.rateme_dialog_title))

            setPositiveButton(R.string.hire) { _, _ ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "plain/text"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("dymnov.dm@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Dymnov Job Offer")
                    putExtra(
                        Intent.EXTRA_TEXT, "Dear Maxim,\n" +
                                "After revising your test application we decided to offer you a position " +
                                "of an Android Developer in NoblyPOS. with the offer details as follows:\n" +
                                "Salary: ..."
                    )
                }

                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."))
                } catch (ignore: Throwable) {
                } finally {
                    saveAnswer(like)
                    mIsNotDialogShowed = false
                }
            }

            setNegativeButton(R.string.nope) { dialog, _ ->
                saveAnswer(nope)
                mIsNotDialogShowed = false
                dialog.dismiss()
            }

            setNeutralButton(R.string.later) { dialog, _ ->
                mIsNotDialogShowed = false
                dialog.dismiss()
            }
        }

        dialog.create().show()
    }

    private fun isHiremeDialogApplicable(): Boolean {
        val answer = readAnswer()

        return mIsNotDialogShowed && !(answer == nope || answer == like)
    }

    private fun saveAnswer(answer: String) {
        val prefs = getPreferences(Context.MODE_PRIVATE)
        prefs.edit()
            .putString(ANSWER_KEY, answer)
            .apply()
    }

    private fun readAnswer(): String? = getPreferences(Context.MODE_PRIVATE).getString(ANSWER_KEY, null) ?: null

    override fun getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun showLoading() {
        runOnUiThread {
            mMenu?.findItem(R.id.miRefresh)?.isEnabled = false
            flSpinner.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            mMenu?.findItem(R.id.miRefresh)?.isEnabled = true
            flSpinner.visibility = View.GONE
        }
    }

    override fun initArticlesInfoList() {
        val viewManager = LinearLayoutManager(this)
        mArticlesInfoAdapter = ArticlesInfoRvAdapter()

        rvArticlesList.apply {
            layoutManager = viewManager
            adapter = mArticlesInfoAdapter
        }
    }

    override fun showArticles(articles: List<Article>) {
        rvArticlesList.visibility = View.VISIBLE
        mArticlesInfoAdapter?.setArticlesInfoList(articles)
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

    override fun hideListEmpty() {
        tvNoArticles.visibility = View.GONE
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
