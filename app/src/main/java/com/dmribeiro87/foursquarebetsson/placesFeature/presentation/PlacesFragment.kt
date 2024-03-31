package com.dmribeiro87.foursquarebetsson.placesFeature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dmribeiro87.foursquarebetsson.R
import com.dmribeiro87.foursquarebetsson.core.util.viewBinding
import com.dmribeiro87.foursquarebetsson.databinding.FragmentPlacesBinding
import com.dmribeiro87.foursquarebetsson.placesFeature.presentation.viewModel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PlacesFragment : Fragment() {

    private val binding: FragmentPlacesBinding by viewBinding()
    private val viewModel : PlacesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.searchPlaces(
                location = "-12.955354,-38.453281",
                categories = "13009",
                radius = 100000,
                minPrice = 1,
                maxPrice = 4,
                openNow = false
            )
        }
    }

}