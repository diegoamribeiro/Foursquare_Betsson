package com.dmribeiro87.foursquarebetsson.placesFeature.domain.repository

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Location

interface LocationService {
    suspend fun getCurrentLocation(): Resource<Location>
}