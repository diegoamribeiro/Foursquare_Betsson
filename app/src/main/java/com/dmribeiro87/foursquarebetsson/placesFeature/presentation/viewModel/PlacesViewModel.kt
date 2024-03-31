package com.dmribeiro87.foursquarebetsson.placesFeature.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.usecase.SearchPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val searchPlacesUseCase: SearchPlacesUseCase
) : ViewModel() {

    fun searchPlaces(location: String, categories: String?, radius: Int, minPrice: Int?, maxPrice: Int?, openNow: Boolean?) {
        viewModelScope.launch {
            val result = searchPlacesUseCase(location, categories, radius, minPrice, maxPrice, openNow)
            Log.d("***Result", result.toString())
        }
    }
}