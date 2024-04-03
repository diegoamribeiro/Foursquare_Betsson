package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model


import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Icon
import com.google.gson.annotations.SerializedName

data class Category(
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