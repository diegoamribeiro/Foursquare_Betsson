package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Circle(
    @SerializedName("center")
    val center: Center,
    @SerializedName("radius")
    val radius: Int
)