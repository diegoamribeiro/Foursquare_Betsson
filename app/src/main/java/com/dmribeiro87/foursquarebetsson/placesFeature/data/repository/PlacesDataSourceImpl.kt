package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource.PlacesRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PlacesDataSourceImpl @Inject constructor(
    private val placesApi: PlacesApi
) : PlacesRemoteDataSource {

    override suspend fun searchNearbyPlaces(
        location: String,
        categories: String?,
        radius: Int,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?,
        fields: String
    ): Response<PlacesResponse> {
        return placesApi.searchNearbyPlaces(location, categories, radius, minPrice, maxPrice, openNow, fields)
    }
}
