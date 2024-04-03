package com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.foursquarebetsson.core.data.remote.model.TipsResponseItem
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceDetailsUseCase
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.usecase.GetPlaceTipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val getPlaceTipsUseCase: GetPlaceTipsUseCase
) : ViewModel() {

    private val _details = MutableLiveData<Resource<PlaceDetails>>()
    val details: LiveData<Resource<PlaceDetails>> = _details

    private val _tips = MutableLiveData<Resource<List<TipItem>>>()
    val tips: LiveData<Resource<List<TipItem>>> = _tips

    fun loadPlaceDetails(placeId: String) {
        _details.value = Resource.Loading()
        viewModelScope.launch {

            val resource = getPlaceDetailsUseCase(placeId)
            loadPlaceTips(placeId)
            _details.value = when (resource) {
                is Resource.Success -> Resource.Success(resource.data)
                is Resource.Error -> Resource.Error(resource.message)
                else -> Resource.Loading()
            }
        }
    }

    private fun loadPlaceTips(placeId: String) {
        _tips.value = Resource.Loading()
        viewModelScope.launch {
            val tipsResult = getPlaceTipsUseCase(placeId)
            _tips.value = when (tipsResult) {
                is Resource.Success -> Resource.Success(tipsResult.data)
                is Resource.Error -> Resource.Error(tipsResult.message)
                else -> Resource.Loading()
            }
        }
    }
}
