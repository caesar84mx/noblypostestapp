package com.caesar_84.noblypostestapp.mainscreen.backstage.model.network

import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

interface ArticlesApiClient {
    fun retrieveLatestNews(): List<Article>
}