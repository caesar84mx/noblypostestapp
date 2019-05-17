package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.model.di.DataAccessModule
import com.caesar_84.noblypostestapp.mainscreen.model.repository.ArticlesDaoTest
import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [DataAccessModule::class]
)
@Singleton
interface TestComponent {
    fun inject(articlesDaoTest: ArticlesDaoTest)
}