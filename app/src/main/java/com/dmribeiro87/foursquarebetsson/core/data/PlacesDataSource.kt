package com.dmribeiro87.foursquarebetsson.core.data

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse

interface PlacesDataSource {

    suspend fun searchNearbyPlaces(
        location: String,
        categories: Int?,
        minPrice: Int,
        price: Int?,
        openNow: Boolean?
    ) : PlacesResponse

}