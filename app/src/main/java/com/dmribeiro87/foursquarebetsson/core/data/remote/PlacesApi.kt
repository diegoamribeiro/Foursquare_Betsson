package com.dmribeiro87.foursquarebetsson.core.data.remote

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.DetailsResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("places/{fsq_id}")
    suspend fun getPlaceDetails(
        @Path("fsq_id") id: String,
        @Query("fields") fields: String?
    ): Response<DetailsResponse>


    @GET("places/{fsq_id}/tips?sort=POPULAR")
    suspend fun getPlaceTips(
        @Path("fsq_id") id: String,
        @Query("fields") fields: String?
    ): Response<TipsResponse>

}