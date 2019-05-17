package com.caesar_84.noblypostestapp.mainscreen.model.repository

import com.caesar_84.noblypostestapp.commons.NoblyPosTestApplication
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [27], application = NoblyPosTestApplication::class)
class ArticlesCacheRepositoryTest {
    @get: Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    var mSubject: ArticlesCacheRepository? = null

    @Mock
    private lateinit var dao: ArticlesDao

    @Before
    fun setUp() {
        initMocks(this)
        mSubject = ArticlesCacheRepository(dao)
    }

    @Test
    fun getCachedNews() {
        val cachedNews = listOf(
                Article(1,"MockTitle1", "www.mockurl1.ru", 250),
                Article(2, "MockTitle1", "www.mockurl2.ru", 280),
                Article(3,"MockTitle3", "www.mockurl3.ru", 300),
                Article(4,"MockTitle4", "www.mockurl4.ru", 350)
        )

        `when`(dao.get()).thenReturn(cachedNews)

        val result = mSubject?.getCachedNews()
        verify(dao).get()
        assertThat(result).isEqualTo(listOf(
                Article(1,"MockTitle1", "www.mockurl1.ru", 250),
                Article(2, "MockTitle1", "www.mockurl2.ru", 280),
                Article(3,"MockTitle3", "www.mockurl3.ru", 300),
                Article(4,"MockTitle4", "www.mockurl4.ru", 350)
        ))
    }

    @Test
    fun cacheNews_noPreviouslyCachedNews() {
        val newsToCache = listOf(
                Article("MockTitle1", "www.mockurl1.ru", 250),
                Article("MockTitle1", "www.mockurl2.ru", 280),
                Article("MockTitle3", "www.mockurl3.ru", 300),
                Article("MockTitle4", "www.mockurl4.ru", 350))

        val cachedNews = listOf(
                Article(1,"MockTitle1", "www.mockurl1.ru", 250),
                Article(2, "MockTitle1", "www.mockurl2.ru", 280),
                Article(3, "MockTitle3", "www.mockurl3.ru", 300),
                Article(4, "MockTitle4", "www.mockurl4.ru", 350))

        `when`(dao.get()).thenReturn(cachedNews)

        mSubject?.cacheNews(newsToCache)
        verify(dao).deleteAll()
        verify(dao).save(newsToCache)

        assertThat(mSubject?.getCachedNews()).isEqualTo(cachedNews)
    }
}