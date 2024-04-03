package com.dmribeiro87.foursquarebetsson.detailsFeature.domain.repository

import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem

interface TipsRepository {
    suspend fun getTipsForPlace(id: String): Resource<List<TipItem>>
}