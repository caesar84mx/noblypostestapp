package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.backstage.di.MainScreenModule
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.di.DataAccessModule
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesCacheRepository
import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import com.caesar_84.noblypostestapp.mainscreen.ui.MainScreen
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
    fun inject(mainScreenPresenter: MainScreenPresenter)
    fun inject(mainScreen: MainScreen)
}