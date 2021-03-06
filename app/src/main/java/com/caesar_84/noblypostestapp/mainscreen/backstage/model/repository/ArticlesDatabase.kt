package com.caesar_84.noblypostestapp.mainscreen.backstage.model.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}