package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails

interface PlaceDetailsRepository {
    suspend fun getPlaceDetails(id: String): Resource<PlaceDetails>
}