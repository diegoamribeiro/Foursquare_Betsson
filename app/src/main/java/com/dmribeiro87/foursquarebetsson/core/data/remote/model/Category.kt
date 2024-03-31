package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("icon")
    val icon: Icon,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("plural_name")
    val pluralName: String,
    @SerializedName("short_name")
    val shortName: String
)