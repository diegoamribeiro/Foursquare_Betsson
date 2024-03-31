package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("results")
    val results: List<PlaceResult>
)