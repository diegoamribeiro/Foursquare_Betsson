package com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place


interface PlacesRepository {

    suspend fun searchNearbyPlaces(
        location: String,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?
    ): Resource<List<Place>>

}