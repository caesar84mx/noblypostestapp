package com.caesar_84.noblypostestapp.mainscreen.model.repository

import com.caesar_84.noblypostestapp.commons.TestApplication
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [27], application = TestApplication::class)
class ArticlesDaoTest {
    @Inject
    lateinit var mSubject: ArticlesDao

    init {
        TestApplication.getInstance().injector.inject(this)
    }

    @After
    fun afterParty() {
        mSubject.deleteAll()
    }

    @Test
    fun daoNotNull() {
        assertThat(mSubject).isNotNull()
    }

    @Test
    fun saveAndGetArticles_test() {
        val newsToSave = listOf(
                Article("MockTitle1", "www.mockurl1.ru", 250),
                Article("MockTitle1", "www.mockurl2.ru", 280),
                Article("MockTitle3", "www.mockurl3.ru", 300),
                Article("MockTitle4", "www.mockurl4.ru", 350))

        val ids = mSubject.save(newsToSave)
        val persistedNews = mSubject.get()

        assertThat(ids).isNotEmpty()
        assertThat(persistedNews).isNotEmpty()
        assertThat(persistedNews[0].id).isNotNull()
    }

    @Test
    fun deleteAll_test() {
        val newsToSave = listOf(
                Article("MockTitle1", "www.mockurl1.ru", 250),
                Article("MockTitle1", "www.mockurl2.ru", 280),
                Article("MockTitle3", "www.mockurl3.ru", 300),
                Article("MockTitle4", "www.mockurl4.ru", 350))

        mSubject.save(newsToSave)

        var persistedNews = mSubject.get()
        assertThat(persistedNews).isNotEmpty()

        mSubject.deleteAll()

        persistedNews = mSubject.get()
        assertThat(persistedNews).isEmpty()
    }
}