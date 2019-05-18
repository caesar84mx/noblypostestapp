package com.caesar_84.noblypostestapp.mainscreen.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.caesar_84.noblypostestapp.R
import com.caesar_84.noblypostestapp.commons.utils.Constants.Configuration.MainScreen.ANSWER_KEY
import com.caesar_84.noblypostestapp.databinding.ActivityMainScreenBinding
import com.caesar_84.noblypostestapp.mainscreen.adapter.ArticlesInfoRvAdapter
import com.caesar_84.noblypostestapp.mainscreen.viewmodel.MainScreenViewModel

class MainScreenActivity : AppCompatActivity() {
    private val nope = "nope"
    private val like = "like"

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel

    private var mIsNotDialogShowed = true

    private var mArticlesInfoAdapter: ArticlesInfoRvAdapter? = null
    private var mMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainScreenViewModel::class.java)
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
}
