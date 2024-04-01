package com.dmribeiro87.foursquarebetsson.placesFeature.domain.model

data class Place(
    val id: String,
    val name: String,
    val categories: List<String>,
    val distance: Int,
    val icon: String,
    val rating: Double,
    val price: Int?,
    val photos: List<Photo>?
)

