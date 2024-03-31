package com.dmribeiro87.foursquarebetsson.placesFeature.data.repository

import android.content.Context
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.PlacesResponse
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.core.util.Utils
import com.dmribeiro87.foursquarebetsson.placesFeature.data.datasource.PlacesRemoteDataSource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PlacesRepositoryImpl @Inject constructor(
    private val placesRemoteDataSource: PlacesRemoteDataSource,
    private val context: Context
) : PlacesRepository {

    override suspend fun searchNearbyPlaces(location: String, categories: String?, radius: Int, minPrice: Int?, maxPrice: Int?, openNow: Boolean?): Resource<List<Place>> {
        if (!Utils.hasInternetConnection(context)) {
            return Resource.Error(message = "No internet connection", data = null)
        }
        return try {
            val response = placesRemoteDataSource.searchNearbyPlaces(location, categories, radius, minPrice, maxPrice, openNow)
            val placesList = response.results.map { placeResponse ->
                Place(
                    id = placeResponse.fsqId,
                    name = placeResponse.name,
                    categories = placeResponse.categories.map { it.name },
                    distance = placeResponse.distance,
                    address = placeResponse.location.address
                )
            }
            Resource.Success(data = placesList)
        } catch (e: Exception) {
            Resource.Error(message = e.message, data = null)
        }
    }

}