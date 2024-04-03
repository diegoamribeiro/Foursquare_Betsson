package com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Location
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository.LocationService
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor (private val locationRepository: LocationService) {
    suspend operator fun invoke(): Resource<Location> {
        return locationRepository.getCurrentLocation()
    }
}
