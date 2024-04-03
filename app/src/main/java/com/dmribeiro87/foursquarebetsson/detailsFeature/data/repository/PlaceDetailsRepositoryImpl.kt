package com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository

import android.util.Log
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.PlaceDetailsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.PlaceDetailsRepository
import javax.inject.Inject

class PlaceDetailsRepositoryImpl @Inject constructor(
    private val dataSource: PlaceDetailsDataSource
): PlaceDetailsRepository {
    override suspend fun getPlaceDetails(id: String): Resource<PlaceDetails> {
        return dataSource.getPlaceDetails(id)
    }
}