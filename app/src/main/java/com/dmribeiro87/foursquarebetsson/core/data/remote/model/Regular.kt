package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Regular(
    @SerializedName("close")
    val close: String,
    @SerializedName("day")
    val day: Int,
    @SerializedName("open")
    val `open`: String
)