package com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MultimediaInfo(
        @SerializedName("url")
        @Expose
        val url: String
)