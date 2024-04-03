package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("context")
    val context: Context? = null,
    @SerializedName("results")
    val results: List<Result>
)