package com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Doc(
        @SerializedName("multimedia")
        @Expose
        val multimedia: List<MultimediaInfo> = listOf(),

        @SerializedName("word_count")
        @Expose
        val wordCount: Int = 0,

        @SerializedName("headline")
        @Expose
        val headline: Headline
)