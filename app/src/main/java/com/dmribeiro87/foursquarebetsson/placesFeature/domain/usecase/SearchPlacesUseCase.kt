package com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase

import android.util.Log
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(private val repository: PlacesRepository) {
    suspend operator fun invoke(
        location: String,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?
    ): Resource<List<Place>> {
        Log.d("SearchPlacesUseCase", "Invoke: isOpen = $openNow")

        return repository.searchNearbyPlaces(location, minPrice, maxPrice, openNow)
    }
}