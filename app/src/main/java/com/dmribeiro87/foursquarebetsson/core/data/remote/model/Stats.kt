package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("total_ratings")
    val totalRatings: Int,
    @SerializedName("total_tips")
    val totalTips: Int
)