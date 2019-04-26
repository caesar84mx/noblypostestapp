package com.caesar_84.noblypostestapp.mainscreen.backstage.model.network

import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.ResponseItem
import com.caesar_84.noblypostestapp.mainscreen.utils.Constants.ApiService.API_KEY
import com.caesar_84.noblypostestapp.mainscreen.utils.Constants.ApiService.ARTICLES_SEARCH_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApiClient {
    @GET("$ARTICLES_SEARCH_ENDPOINT?api-key=$API_KEY&fl=headline,word_count,multimedia&begin_date={date}")
    fun retrieveNewsInfo(@Path("date") fromDateString: String): Call<List<ResponseItem>>
}