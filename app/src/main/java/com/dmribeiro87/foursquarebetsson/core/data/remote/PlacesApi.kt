package com.dmribeiro87.foursquarebetsson.core.data.remote

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {

    @GET("places/search")
    suspend fun searchNearbyPlaces(
        @Query("ll") location: String,
        @Query("categories") categories: String?,
        @Query("radius") radius: Int,
        @Query("min_price") minPrice: Int?,
        @Query("max_price") maxPrice: Int?,
        @Query("open_now") openNow: Boolean?,
        @Query("fields") fields: String?
    ): Response<PlacesResponse>

}