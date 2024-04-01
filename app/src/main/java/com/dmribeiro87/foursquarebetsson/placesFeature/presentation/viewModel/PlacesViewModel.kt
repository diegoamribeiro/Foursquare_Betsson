package com.dmribeiro87.foursquarebetsson.placesFeature.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.SearchPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val searchPlacesUseCase: SearchPlacesUseCase
) : ViewModel() {


    private val _placesData = MutableLiveData<Resource<List<Place>>>()
    var placesData: LiveData<Resource<List<Place>>> = _placesData

    private var isOpen = false
    private var price = 1

    fun searchPlaces() {
        _placesData.value = Resource.Loading()

        viewModelScope.launch {
            Log.d("***State", isOpen.toString())
            val resource = searchPlacesUseCase(minPrice = price, maxPrice = price, isOpen)


            _placesData.value = when(resource) {
                is Resource.Success -> {
                    Resource.Success(resource.data)
                }
                is Resource.Error -> {
                    Resource.Error(resource.message, resource.data)
                }
                is Resource.Loading -> {
                    Resource.Loading()
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

        Log.d("PlacesViewModel", "updateFilters: isOpen = $isOpen")

        this.isOpen = isOpen
        searchPlaces()
    }


}