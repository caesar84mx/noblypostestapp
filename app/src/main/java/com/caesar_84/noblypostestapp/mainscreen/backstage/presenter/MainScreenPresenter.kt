package com.caesar_84.noblypostestapp.mainscreen.backstage.presenter

import com.caesar_84.noblypostestapp.commons.mvpabstracts.presenter.BasePresenter
import com.caesar_84.noblypostestapp.mainscreen.MainScreenContract
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.network.ArticlesApiClient
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesDao

class MainScreenPresenter: BasePresenter<MainScreenContract.View>(), MainScreenContract.Presenter {
    lateinit var mApiClient: ArticlesApiClient
    lateinit var mArticlesDao: ArticlesDao

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
                 cacheNews(articles)
             } else {
                 view?.showListEmpty()
             }
        } else {
            view?.hideLoadingIndicator()
            view?.showErrorMessage(
                "No Connection",
                "Internet connection could not be established. Please, check your network settings")
            val articles = mArticlesDao.getCachedNews()

            if (articles.isNotEmpty()) {
               view?.showArticles(articles)
            } else {
                view?.showListEmpty()
            }
        }
    }

    private fun cacheNews(articles: List<Article>) {
        mArticlesDao.wipeOldCache()
        mArticlesDao.cacheNews(articles)
    }

    private fun isConnectionAvailable(): Boolean {
        TODO("not implemented")
    }
}