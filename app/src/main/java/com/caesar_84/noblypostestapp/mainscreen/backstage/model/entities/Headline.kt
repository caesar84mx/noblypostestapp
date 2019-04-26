package com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Headline(
        @SerializedName("main")
        @Expose
        val main: String,

        @SerializedName("kicker")
        @Expose
        val kicker: String,

        @SerializedName("content_kicker")
        @Expose
        val contentKicker: String,

        @SerializedName("print_headline")
        @Expose
        val printHeadline: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("seo")
        @Expose
        val seo: String,

        @SerializedName("sub")
        @Expose
        val sub: String
)