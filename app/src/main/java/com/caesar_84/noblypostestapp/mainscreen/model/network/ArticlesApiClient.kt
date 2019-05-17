package com.caesar_84.noblypostestapp.mainscreen.model.network

import com.caesar_84.noblypostestapp.commons.utils.Constants.ApiService.API_KEY
import com.caesar_84.noblypostestapp.commons.utils.Constants.ApiService.ARTICLES_SEARCH_ENDPOINT
import com.caesar_84.noblypostestapp.mainscreen.model.entities.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiClient {
    @GET("$ARTICLES_SEARCH_ENDPOINT?api-key=$API_KEY&fl=headline,word_count,multimedia&")

    fun retrieveNewsInfo(@Query("begin_date") beginDate: String): Call<DataResponse>
}