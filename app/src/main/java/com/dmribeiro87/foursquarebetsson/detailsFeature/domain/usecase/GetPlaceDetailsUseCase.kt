package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase

import android.util.Log
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.PlaceDetailsRepository
import javax.inject.Inject

class GetPlaceDetailsUseCase @Inject constructor(
    private val placeDetailsRepository: PlaceDetailsRepository
) {
    suspend operator fun invoke(id: String): Resource<PlaceDetails> {
        return placeDetailsRepository.getPlaceDetails(id)
    }
}