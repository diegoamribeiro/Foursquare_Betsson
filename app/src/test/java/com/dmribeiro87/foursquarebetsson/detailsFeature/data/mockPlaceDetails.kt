package com.dmribeiro87.foursquarebetsson.detailsFeature.data

import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.Stats

val mockPlaceDetails = PlaceDetails(
    id = "1",
    name = "Mock Place",
    price = 2,
    category = "Mock Category",
    rating = 4.5,
    description = "This is a mock description of a place.",
    location = "Mock Location, Mock Street",
    tel = "123-456-7890",
    hours = "9am - 5pm",
    stats = Stats(
        checkinsCount = 100,
        usersCount = 50,
        photoCount = 10
    ),
    photos = listOf(
        "https://example.com/photo1.jpg",
        "https://example.com/photo2.jpg"
    ),
    tips = listOf(
        "This is a mock tip 1.",
        "This is a mock tip 2."
    )
)
