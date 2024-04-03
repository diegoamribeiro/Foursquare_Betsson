package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.TipsRepository

class GetPlaceTipsUseCase(
    private val repository: TipsRepository
) {

    suspend operator fun invoke(fsqId: String): Resource<List<TipItem>> {
        return repository.getTipsForPlace(fsqId)
    }
}
