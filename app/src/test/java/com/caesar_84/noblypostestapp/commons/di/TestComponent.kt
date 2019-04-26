package com.caesar_84.noblypostestapp.commons.di

import com.caesar_84.noblypostestapp.mainscreen.backstage.model.di.DataAccessModule
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository.ArticlesDaoTest
import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [DataAccessModule::class]
)
@Singleton
interface TestComponent {
    fun inject(articlesDaoTest: ArticlesDaoTest)
}