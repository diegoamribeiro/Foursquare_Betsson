package com.dmribeiro87.foursquarebetsson.placesFeature.domain

data class Place(
    val id: String,
    val name: String,
    val categories: List<String>,
    val distance: Int,
    val address: String
)

