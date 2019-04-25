package com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "image_url")
        var imageUrl: String? = null,

        @ColumnInfo(name = "word_count")
        var wordCount: Int = 0
) : Parcelable {
    @Ignore
    constructor(
            title: String?,
            imageUrl: String?,
            wordCount: Int
    ): this(null, title, imageUrl, wordCount)
}