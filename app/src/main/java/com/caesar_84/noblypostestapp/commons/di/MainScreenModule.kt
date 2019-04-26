package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule {
    @Provides
    fun providesMainScreenPresenter(): MainScreenPresenter = MainScreenPresenter()
}