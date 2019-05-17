package com.caesar_84.noblypostestapp.mainscreen.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Headline(
        @SerializedName("main")
        @Expose
        val main: String
)