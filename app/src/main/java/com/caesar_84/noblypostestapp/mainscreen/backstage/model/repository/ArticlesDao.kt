package com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository

import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

interface ArticlesDao {
    fun getCachedNews(): List<Article>
    fun cacheNews(articles: List<Article>)
    fun wipeOldCache()
}