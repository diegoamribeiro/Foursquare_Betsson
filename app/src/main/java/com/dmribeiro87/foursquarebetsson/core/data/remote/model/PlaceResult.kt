package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaceResult(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("chains")
    val chains: List<Chain>,
    @SerializedName("closed_bucket")
    val closedBucket: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("geocodes")
    val geocodes: Geocodes,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces,
    @SerializedName("timezone")
    val timezone: String
)