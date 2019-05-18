package com.caesar_84.noblypostestapp.mainscreen.di

import android.content.Context
import androidx.room.Room
import com.caesar_84.noblypostestapp.mainscreen.model.network.ArticlesApiClient
import com.caesar_84.noblypostestapp.mainscreen.model.repository.ArticlesCacheRepository
import com.caesar_84.noblypostestapp.mainscreen.model.repository.ArticlesDao
import com.caesar_84.noblypostestapp.mainscreen.model.repository.ArticlesDatabase
import com.caesar_84.noblypostestapp.commons.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class DataAccessModule(context: Context) {
    private val database: ArticlesDatabase = Room.databaseBuilder(
            context,
            ArticlesDatabase::class.java,
            "points"
    )
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun providesDao(): ArticlesDao = database.getArticlesDao()

    @Provides
    @Singleton
    fun providesArticlesRepository(dao: ArticlesDao) = ArticlesCacheRepository(dao)

    @Provides
    @Singleton
    fun providesArticlesApiClient(): ArticlesApiClient = Retrofit.Builder()
            .baseUrl(Constants.ApiService.NYT_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticlesApiClient::class.java)
}