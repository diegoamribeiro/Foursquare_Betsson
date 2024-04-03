package com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.TipsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository.TipsRepository

class TipsRepositoryImpl(
    private val tipsDataSource: TipsDataSource
) : TipsRepository {

    override suspend fun getTipsForPlace(id: String): Resource<List<TipItem>> {
        return tipsDataSource.getPlaceTips(id)
    }
}
