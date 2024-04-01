package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("photos")
    val photos: List<PhotoResponse>?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("rating")
    val rating: Double?,

    )