package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("classifications")
    val classifications: List<String>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("suffix")
    val suffix: String?,
    @SerializedName("width")
    val width: Int?
)