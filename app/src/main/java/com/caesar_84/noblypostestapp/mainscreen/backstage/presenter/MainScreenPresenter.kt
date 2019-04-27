package com.caesar_84.noblypostestapp.mainscreen.backstage.presenter

import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.BasePresenter
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Doc
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.network.ArticlesApiClient
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesCacheRepository
import com.caesar_84.noblypostestapp.commons.utils.DateHelper
import com.caesar_84.noblypostestapp.commons.utils.LogHelper
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

class MainScreenPresenter : BasePresenter<MainScreenContract.View>(), MainScreenContract.Presenter {
    @Inject
    protected lateinit var mApiClient: ArticlesApiClient

    @Inject
    protected lateinit var mArticlesCacheRepository: ArticlesCacheRepository

    init {
        NoblyPosTestApplication.getInstance().injector.inject(this)
    }

    override fun presentScreen() {
        view?.showListEmpty(true)
        view?.initArticlesInfoList()
        retrieveAndShowArticles()
    }

    override fun refreshArticles() {
        retrieveAndShowArticles()
    }

    private fun retrieveAndShowArticles() {
        LogHelper.logToConsole("Retrieving articles")

        view?.showLoading(true)

        if (isNetworkAvailable()) {
            LogHelper.logToConsole("Making service call")

            val serviceCall = mApiClient.retrieveNewsInfo(DateHelper.getDateString(LocalDate.now()))
            serviceCall.enqueue(NewsInfoCallback())
        } else {
            LogHelper.logToConsole("No network. Loading from cache.")

            val articles = mArticlesCacheRepository.getCachedNews()

            view?.showLoading(false)
            view?.showErrorMessage(
                view!!.getNoConnectionDialogTitle(),
                view!!.getNoConnectionDialogMessage()
            )

            if (articles.isNotEmpty()) {
                LogHelper.logToConsole(articles)

                view?.showListEmpty(false)
                view?.showArticles(articles)
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo = view?.getConnectivityManager()?.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private inner class NewsInfoCallback : Callback<DataResponse> {
        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
            LogHelper.logToConsole("Failed to retrieve data")
            LogHelper.logToConsole(t)

            view?.showLoading(false)
            view?.showErrorMessage(
                "Error",
                "An error occurred while retrieving new articles"
            )
        }

        override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
            LogHelper.logToConsole("Data retrieve successful")

            view?.showLoading(false)
            val responseItems = response.body()?.response?.docs ?: emptyList()

            if (responseItems.isNotEmpty()) {
                val articles = responseItems.map { item -> Article(item) }

                view?.showListEmpty(false)
                view?.showArticles(articles)
                mArticlesCacheRepository.cacheNews(articles)
            } else {
                view?.showListEmpty(true)
                LogHelper.logToConsole("Empty list retrieved")
            }
        }

    }
}