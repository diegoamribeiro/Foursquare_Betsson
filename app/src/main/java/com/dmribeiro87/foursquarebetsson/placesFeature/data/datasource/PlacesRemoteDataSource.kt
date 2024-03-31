package com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource

import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor (private val api: PlacesApi) {

    suspend fun searchNearbyPlaces(
        location: String,
        categories: String?,
        radius: Int,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?
    ) = api.searchNearbyPlaces(location, categories, radius, minPrice, maxPrice, openNow).body()
        ?: throw Exception("Failed to load data")
}