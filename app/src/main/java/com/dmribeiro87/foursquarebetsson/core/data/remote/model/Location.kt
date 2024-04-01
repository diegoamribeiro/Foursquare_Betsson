package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("cross_street")
    val crossStreet: String,
    @SerializedName("formatted_address")
    val formattedAddress: String
)