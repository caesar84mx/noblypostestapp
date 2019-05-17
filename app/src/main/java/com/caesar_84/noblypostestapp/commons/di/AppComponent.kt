package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.di.DataAccessModule
import com.caesar_84.noblypostestapp.mainscreen.model.repository.ArticlesCacheRepository
import com.caesar_84.noblypostestapp.mainscreen.view.MainScreenActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DataAccessModule::class,
        MainScreenModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(articlesCacheRepository: ArticlesCacheRepository)
    fun inject(mainScreenActivity: MainScreenActivity)
}