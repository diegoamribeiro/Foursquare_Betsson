package com.dmribeiro87.foursquarebetsson.detailsFeature.data.repository

import com.dmribeiro87.foursquarebetsson.core.data.remote.PlacesApi
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import com.dmribeiro87.foursquarebetsson.core.util.Constants
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.data.datasource.TipsDataSource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem

class TipsDataSourceImpl(
    private val placesApi: PlacesApi
) : TipsDataSource {

    private val FIELDS = "${Constants.CREATED_AT},${Constants.ID},${Constants.PHOTO},${Constants.TEXT}"

    override suspend fun getPlaceTips(id: String): Resource<List<TipItem>> {
        return try {
            val response = placesApi.getPlaceTips(id, FIELDS)
            if (response.isSuccessful) {
                val tipsResponseItems = response.body() ?: emptyList()
                val tips = tipsResponseItems.map { it.toDomain() }
                Resource.Success(tips)
            } else {
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.message}")
        }
    }

    // Função de extensão para converter TipsResponseItem em TipItem
    fun TipsResponseItem.toDomain(): TipItem {
        return TipItem(
            createdAt = this.createdAt ?: "",
            id = this.id ?: "",
            photoUrl = this.photo?.let { photo ->
                "${photo.prefix}${photo.width}x${photo.height}${photo.suffix}"
            } ?: "",
            text = this.text ?: ""
        )
    }

}
