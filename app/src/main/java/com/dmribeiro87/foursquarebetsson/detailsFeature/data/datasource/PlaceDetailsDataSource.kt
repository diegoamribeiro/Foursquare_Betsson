package com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails

interface PlaceDetailsDataSource {
    suspend fun getPlaceDetails(id: String): Resource<PlaceDetails>
}
