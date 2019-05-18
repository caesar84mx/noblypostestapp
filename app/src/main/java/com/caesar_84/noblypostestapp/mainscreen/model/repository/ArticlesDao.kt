package com.caesar_84.noblypostestapp.mainscreen.model.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM articles")
    fun get(): List<Article>

    @Query("DELETE FROM articles")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(articles: List<Article>): LongArray
}