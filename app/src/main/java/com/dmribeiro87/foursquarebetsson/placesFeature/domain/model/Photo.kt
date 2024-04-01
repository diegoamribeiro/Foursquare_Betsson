package com.dmribeiro87.foursquarebetsson.placesFeature.domain.model

data class Photo(
    val classifications: List<String>,
    val createdAt: String,
    val height: Int,
    val id: String,
    val prefix: String,
    val suffix: String,
    val width: Int
)