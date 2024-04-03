package com.dmribeiro87.foursquarebetsson.placesFeature.data

import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Photo
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place

val mockPlace = Place(
    id = "1",
    name = "Mock Place",
    categories = listOf("Category 1"),
    distance = 100,
    icon = "https://example.com/icon.png",
    rating = 4.5,
    price = 1,
    photos = listOf(
        Photo(
            classifications = emptyList(),
            createdAt = "2021-01-01T00:00:00.000Z",
            height = 100,
            id = "photo1",
            prefix = "https://example.com/",
            suffix = "/photo.png",
            width = 100
        )
    )
)

val mockPlaceList = listOf(mockPlace)