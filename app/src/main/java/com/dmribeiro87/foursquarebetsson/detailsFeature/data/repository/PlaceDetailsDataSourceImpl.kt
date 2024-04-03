package com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository

import android.util.Log
import com.dmribeiro87.foursquarebetsson.BuildConfig
import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.DetailsResponse
import com.dmribeiro87.foursquarebetsson.core.util.Constants
import com.dmribeiro87.foursquarebetsson.core.util.Constants.CATEGORIES
import com.dmribeiro87.foursquarebetsson.core.util.Constants.DESCRIPTION
import com.dmribeiro87.foursquarebetsson.core.util.Constants.FSQ_ID
import com.dmribeiro87.foursquarebetsson.core.util.Constants.HOURS
import com.dmribeiro87.foursquarebetsson.core.util.Constants.LOCATION
import com.dmribeiro87.foursquarebetsson.core.util.Constants.NAME
import com.dmribeiro87.foursquarebetsson.core.util.Constants.PHOTOS
import com.dmribeiro87.foursquarebetsson.core.util.Constants.PRICE
import com.dmribeiro87.foursquarebetsson.core.util.Constants.RATING
import com.dmribeiro87.foursquarebetsson.core.util.Constants.STATS
import com.dmribeiro87.foursquarebetsson.core.util.Constants.TEL
import com.dmribeiro87.foursquarebetsson.core.util.Constants.TIPS
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.PlaceDetailsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.Stats
import javax.inject.Inject

class PlaceDetailsDataSourceImpl @Inject constructor(
    private val placesApi: PlacesApi
) : PlaceDetailsDataSource {

    val ALL_FIELDS = listOf(
        NAME, PRICE, CATEGORIES, RATING, DESCRIPTION,
        LOCATION, TEL, HOURS, STATS, PHOTOS, TIPS, FSQ_ID
    ).joinToString(separator = ",")

    override suspend fun getPlaceDetails(id: String): Resource<PlaceDetails> {
        return try {
            val response = placesApi.getPlaceDetails(id, ALL_FIELDS)
            if (response.isSuccessful && response.body() != null) {
                val placeDetails = convertDetailsResponseToDomain(response.body()!!)
                Resource.Success(placeDetails)
            } else {
                Resource.Error("API call was not successful", null)
            }
        } catch (e: Exception) {
            Resource.Error("Failed to fetch place details: ${e.message}", null)
        }
    }

    private fun convertDetailsResponseToDomain(detailsResponse: DetailsResponse): PlaceDetails {
        return PlaceDetails(
            id = detailsResponse.id,
            name = detailsResponse.name,
            price = detailsResponse.price,
            category = detailsResponse.categories.firstOrNull { it.id.toString() == BuildConfig.CATEGORY_ID }!!.name,
            rating = detailsResponse.rating,
            description = "",
            location = "${detailsResponse.location.formattedAddress}, ${detailsResponse.location.crossStreet}",
            tel = detailsResponse.tel ?: "N/A",
            hours = detailsResponse.hours.display,
            stats = Stats(
                checkinsCount = detailsResponse.stats.totalRatings, // Ajuste conforme necessário
                usersCount = detailsResponse.stats.totalTips, // Ajuste conforme necessário
                photoCount = detailsResponse.stats.totalPhotos
            ),
            photos = detailsResponse.photos.map { it.prefix + "300x300" + it.suffix },
            tips = detailsResponse.tips.map { it.text }
        )
    }

}