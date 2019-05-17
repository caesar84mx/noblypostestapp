package com.caesar_84.noblypostestapp.mainscreen.model.entities

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
        val id: Int?,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "image_url")
        val imageUrl: String,

        @ColumnInfo(name = "word_count")
        val wordCount: Int = 0
) : Parcelable {
    @Ignore
    constructor(
            title: String,
            imageUrl: String,
            wordCount: Int
    ): this(null, title, imageUrl, wordCount)

    @Ignore
    constructor(doc: Doc) : this(
            doc.headline.main,
            if (doc.multimedia.isEmpty()) "" else doc.multimedia[0].url,
            doc.wordCount
    )
}