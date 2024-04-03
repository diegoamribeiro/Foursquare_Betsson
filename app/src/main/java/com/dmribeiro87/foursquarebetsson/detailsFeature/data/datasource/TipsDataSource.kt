package com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource

import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem

interface TipsDataSource {
    suspend fun getPlaceTips(id: String): Resource<List<TipItem>>
}
