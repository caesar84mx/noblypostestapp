package com.caesar_84.noblypostestapp.mainscreen.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("copyright")
    @Expose
    val copyright: String,

    @SerializedName("response")
    @Expose
    val response: ResponseData
)