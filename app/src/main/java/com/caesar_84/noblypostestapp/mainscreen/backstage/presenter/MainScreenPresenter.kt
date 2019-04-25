package com.caesar_84.noblypostestapp.mainscreen.backstage.presenter

import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.BasePresenter
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.network.ArticlesApiClient
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesCacheRepository

class MainScreenPresenter: BasePresenter<MainScreenContract.View>(), MainScreenContract.Presenter {
    protected lateinit var mApiClient: ArticlesApiClient
    protected lateinit var mArticlesCacheRepository: ArticlesCacheRepository

//    init {
//        NoblyPosTestApplication.getInjector().inject(this)
//    }

    override fun presentScreen() {
        retrieveAndShowArticles()
    }

    override fun refreshArticles() {
        retrieveAndShowArticles()
    }

    private fun retrieveAndShowArticles() {
        view?.showLoadingIndicator()

        if (isConnectionAvailable()) {
            val articles = mApiClient.retrieveLatestNews()
            view?.hideLoadingIndicator()

             if (articles.isNotEmpty()) {
                 view?.showArticles(articles)
                 mArticlesCacheRepository.cacheNews(articles)
             } else {
                 view?.showErrorMessage(
                         "Error",
                         "An error occurred on retrieveing new articles"
                 )
                 view?.showListEmpty()
             }
        } else {
            view?.hideLoadingIndicator()
            view?.showErrorMessage(
                "No Connection",
                "Internet connection could not be established. Please, check your network settings")
            val articles = mArticlesCacheRepository.getCachedNews()

            if (articles.isNotEmpty()) {
               view?.showArticles(articles)
            } else {
                view?.showListEmpty()
            }
        }
    }

    private fun isConnectionAvailable(): Boolean {
        TODO("not implemented")
    }
}