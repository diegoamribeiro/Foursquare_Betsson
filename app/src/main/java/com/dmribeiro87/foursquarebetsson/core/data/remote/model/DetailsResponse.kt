package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("fsq_id")
    val id: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("hours")
    val hours: Hours,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("tips")
    val tips: List<Tip>
)