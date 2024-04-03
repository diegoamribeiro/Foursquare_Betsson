package com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import retrofit2.Response

interface PlacesRemoteDataSource {
    suspend fun searchNearbyPlaces(
        location: String,
        categories: String?,
        radius: Int,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?,
        fields: String
    ): Response<PlacesResponse>
}
