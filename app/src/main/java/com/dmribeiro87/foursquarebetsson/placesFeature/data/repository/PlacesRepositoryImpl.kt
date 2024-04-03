package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import android.content.Context
import com.dmribeiro87.foursquarebetsson.BuildConfig
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PhotoResponse
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.Result
import com.dmribeiro87.foursquarebetsson.core.util.Constants.CATEGORIES
import com.dmribeiro87.foursquarebetsson.core.util.Constants.DISTANCE
import com.dmribeiro87.foursquarebetsson.core.util.Constants.FSQ_ID
import com.dmribeiro87.foursquarebetsson.core.util.Constants.ICON_SIZE
import com.dmribeiro87.foursquarebetsson.core.util.Constants.LOCATION
import com.dmribeiro87.foursquarebetsson.core.util.Constants.NAME
import com.dmribeiro87.foursquarebetsson.core.util.Constants.PHOTOS
import com.dmribeiro87.foursquarebetsson.core.util.Constants.PRICE
import com.dmribeiro87.foursquarebetsson.core.util.Constants.RATING
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.core.util.Utils
import com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource.PlacesRemoteDataSource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Photo
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import javax.inject.Inject


class PlacesRepositoryImpl @Inject constructor(
    private val placesRemoteDataSource: PlacesRemoteDataSource
) : PlacesRepository {

    private val categoryId = BuildConfig.CATEGORY_ID
    private val fieldsList = listOf(FSQ_ID, CATEGORIES, NAME, PRICE, PHOTOS, LOCATION, RATING, DISTANCE)
    private val fieldsString = fieldsList.joinToString(separator = ",")
    private val radius = BuildConfig.RADIUS


    override suspend fun searchNearbyPlaces(
        location: String,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?
    ): Resource<List<Place>> {

        return try {
            val response = placesRemoteDataSource.searchNearbyPlaces(
                location,
                categoryId,
                radius,
                minPrice,
                maxPrice,
                openNow,
                fieldsString
            )

            if (response.isSuccessful && response.body() != null) {
                val placesList = response.body()!!.results.map { result: Result ->
                    val iconUrl = result.categories.firstOrNull { it.id.toString() == categoryId }?.icon?.let {
                        "${it.prefix}$ICON_SIZE${it.suffix.trim()}"
                    } ?: ""

                    Place(
                        id = result.fsqId,
                        name = result.name,
                        categories = result.categories.map { it.name },
                        distance = result.distance,
                        icon = iconUrl,
                        rating = result.rating ?: 0.0,
                        price = result.price ?: 1,
                        photos = result.photos?.map { it.toPhoto() } ?: emptyList()
                    )
                }
                Resource.Success(data = placesList)
            } else {
                Resource.Error(message = response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown error occurred")
        }
    }

    private fun PhotoResponse?.toPhoto() = Photo(
        classifications = this?.classifications ?: emptyList(),
        createdAt = this?.createdAt ?: "",
        height = this?.height ?: 0,
        id = this?.id ?: "",
        prefix = this?.prefix ?: "",
        suffix = this?.suffix ?: "",
        width = this?.width ?: 0
    )


}