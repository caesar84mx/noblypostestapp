package com.caesar_84.noblypostestapp.mainscreen.model.repository

import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article
import javax.inject.Inject

class ArticlesCacheRepository @Inject constructor(protected val dao: ArticlesDao) {
    init {
        NoblyPosTestApplication.getInstance().injector.inject(this)
    }

    fun getCachedNews(): List<Article> {
        return dao.get()
    }

    fun cacheNews(articles: List<Article>) {
        wipeOldCache()
        dao.save(articles)
    }

    private fun wipeOldCache() {
        dao.deleteAll()
    }
}