package com.dmribeiro87.foursquarebetsson.placesFeature.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.GetCurrentLocationUseCase
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.SearchPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val searchPlacesUseCase: SearchPlacesUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    private val _placesData = MutableLiveData<Resource<List<Place>>>()
    var placesData: LiveData<Resource<List<Place>>> = _placesData

    private var isOpen = false
    private var price = 1
    private var location = ""

    private fun searchPlaces() {
        _placesData.value = Resource.Loading()
        viewModelScope.launch {

            val resource = searchPlacesUseCase(location, minPrice = price, maxPrice = price, isOpen)

            _placesData.value = when(resource) {
                is Resource.Success -> Resource.Success(resource.data)
                is Resource.Error -> Resource.Error(resource.message, resource.data)
                else -> Resource.Loading()
            }
        }
    }

    fun getCurrentLocationAndSearchPlaces() {
        viewModelScope.launch {
            val locationResource = getCurrentLocationUseCase()
            when (locationResource) {
                is Resource.Success -> {
                    location = "${locationResource.data?.latitude},${locationResource.data?.longitude}"
                    searchPlaces()
                }
                is Resource.Error -> {
                    _placesData.postValue(Resource.Error("Error obtaining location: ${locationResource.message}", null))
                }
                is Resource.Loading -> {
                    _placesData.postValue(Resource.Loading())
                }
            }
        }
    }

    fun updateFilters(price: String, isOpen: Boolean) {
        val newPrice = when (price) {
            "$" -> 1
            "$$" -> 2
            "$$$" -> 3
            "$$$$" -> 4
            else -> 1
        }

        if (this.price != newPrice || this.isOpen != isOpen) {
            this.price = newPrice
            this.isOpen = isOpen
            searchPlaces()
        }
        this.isOpen = isOpen
        searchPlaces()
    }
}