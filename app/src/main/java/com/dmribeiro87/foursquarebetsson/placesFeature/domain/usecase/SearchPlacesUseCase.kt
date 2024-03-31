package com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase

import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.PlacesRepository
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(private val repository: PlacesRepository) {
    suspend operator fun invoke(
        location: String,
        categories: String?,
        radius: Int,
        minPrice: Int?,
        maxPrice: Int?,
        openNow: Boolean?
    ) = repository.searchNearbyPlaces(location, categories, radius, minPrice, maxPrice, openNow)
}