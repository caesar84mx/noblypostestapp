package com.caesar_84.noblypostestapp.mainscreen.backstage.presenter

import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.BasePresenter
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.ResponseItem
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.network.ArticlesApiClient
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesCacheRepository
import com.caesar_84.noblypostestapp.mainscreen.utils.DateHelper
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
        retrieveAndShowArticles()
    }

    override fun refreshArticles() {
        retrieveAndShowArticles()
    }

    private fun retrieveAndShowArticles() {
        view?.showLoadingIndicator()

        if (isNetworkAvailable()) {
            val serviceCall = mApiClient.retrieveNewsInfo(DateHelper.getDateString(LocalDate.now()))
            serviceCall.enqueue(NewsInfoCallback())
        } else {
            val articles = mArticlesCacheRepository.getCachedNews()

            view?.hideLoadingIndicator()
            view?.showErrorMessage(
                    "No Connection",
                    "Internet connection could not be established. Please, check your network settings")

            if (articles.isNotEmpty()) {
                view?.showListEmpty(false)
                view?.showArticles(articles)
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo = view?.getConnectivityManager()?.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private inner class NewsInfoCallback : Callback<List<ResponseItem>> {
        override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
            view?.hideLoadingIndicator()
            view?.showErrorMessage(
                    "Error",
                    "An error occurred on retrieving new articles"
            )
        }

        override fun onResponse(call: Call<List<ResponseItem>>, response: Response<List<ResponseItem>>) {
            val responseItems = response.body() ?: emptyList()

            if (responseItems.isNotEmpty()) {
                val articles = responseItems.map { item -> Article(item) }

                view?.showArticles(articles)
                mArticlesCacheRepository.cacheNews(articles)
            } else {
                view?.showListEmpty(true)
            }
        }

    }
}