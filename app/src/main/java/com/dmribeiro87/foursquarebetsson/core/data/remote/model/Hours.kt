package com.dmribeiro87.foursquarebetsson.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Hours(
    @SerializedName("display")
    val display: String,
    @SerializedName("is_local_holiday")
    val isLocalHoliday: Boolean,
    @SerializedName("open_now")
    val openNow: Boolean,
    @SerializedName("regular")
    val regular: List<Regular>
)