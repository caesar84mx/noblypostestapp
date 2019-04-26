package com.caesar_84.noblypostestapp.mainscreen.backstage.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MultimediaInfo(
        @SerializedName("rank")
        @Expose
        val rank: Int = 0,

        @SerializedName("subtype")
        @Expose
        val subtype: String,

        @SerializedName("caption")
        @Expose
        val caption: String,

        @SerializedName("credit")
        @Expose
        val credit: String,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("url")
        @Expose
        val url: String,

        @SerializedName("height")
        @Expose
        val height: Int = 0,

        @SerializedName("width")
        @Expose
        val width: Int = 0,

        @SerializedName("subType")
        @Expose
        val subType: String,

        @SerializedName("crop_name")
        @Expose
        var cropName: String
)