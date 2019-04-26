package com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM articles")
    fun get(): List<Article>

    @Query("DELETE FROM articles")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(articles: List<Article>): LongArray
}