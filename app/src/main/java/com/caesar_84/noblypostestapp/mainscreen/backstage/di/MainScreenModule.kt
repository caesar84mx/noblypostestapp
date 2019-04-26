package com.caesar_84.noblypostestapp.mainscreen.backstage.di

import com.caesar_84.noblypostestapp.mainscreen.backstage.presenter.MainScreenPresenter
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule {
    @Provides
    fun providesMainScreenPresenter(): MainScreenPresenter = MainScreenPresenter()
}