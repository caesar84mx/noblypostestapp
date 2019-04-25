package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.backstage.model.di.DataAccessModule
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesCacheRepository
import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [
            DataAccessModule::class
        ]
)
@Singleton
interface AppComponent {
    fun inject(articlesCacheRepository: ArticlesCacheRepository)
}