package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import android.content.Context
import com.dmribeiro87.foursquarebetsson.BuildConfig
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PhotoResponse
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
    private val placesRemoteDataSource: PlacesRemoteDataSource,
    private val context: Context
) : PlacesRepository {

    private val categoryId = BuildConfig.CATEGORY_ID
    private val fieldsList = listOf(FSQ_ID, CATEGORIES, NAME, PRICE, PHOTOS, LOCATION, RATING, DISTANCE)
    private val fieldsString = fieldsList.joinToString(separator = ",")
    private val radius = BuildConfig.RADIUS


    override suspend fun searchNearbyPlaces(minPrice: Int?, maxPrice: Int?, openNow: Boolean?): Resource<List<Place>> {
        if (!Utils.hasInternetConnection(context)) {
            return Resource.Error(message = "No internet connection", data = null)
        }
        return try {

            val response = placesRemoteDataSource.searchNearbyPlaces(
                "-12.955354,-38.453281",
                categoryId,
                radius,
                minPrice,
                maxPrice,
                openNow,
                fieldsString)
            val placesList = response.results.map { placeResponse ->

                val matchingCategory = placeResponse.categories.firstOrNull { it.id.toString() == categoryId }
                val iconUrl = if (matchingCategory != null) {
                    "${matchingCategory.icon.prefix}$ICON_SIZE${matchingCategory.icon.suffix.trim()}"
                } else {
                    placeResponse.categories[0].toString()
                }

                Place(
                    id = placeResponse.fsqId,
                    name = placeResponse.name,
                    categories = placeResponse.categories.map { it.name },
                    distance = placeResponse.distance,
                    icon = iconUrl,
                    rating = placeResponse.rating ?: 0.0,
                    price = placeResponse.price ?: 1,
                    photos = placeResponse.photos?.map { it.toPhoto() } ?: emptyList()
                )
            }
            Resource.Success(data = placesList)
        } catch (e: Exception) {
            Resource.Error(message = e.message, data = null)
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