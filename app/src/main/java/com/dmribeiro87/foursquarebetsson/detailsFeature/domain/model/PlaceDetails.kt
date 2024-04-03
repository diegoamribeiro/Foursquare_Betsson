package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model


data class PlaceDetails(
    val id: String,
    val name: String,
    val price: Int,
    val category: String,
    val rating: Double,
    val description: String?,
    val location: String,
    val tel: String?,
    val hours: String?,
    val stats: Stats?,
    val photos: List<String>,
    val tips: List<String>
)
