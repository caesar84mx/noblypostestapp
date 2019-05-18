package com.caesar_84.noblypostestapp.mainscreen.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caesar_84.noblypostestapp.mainscreen.model.entities.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}