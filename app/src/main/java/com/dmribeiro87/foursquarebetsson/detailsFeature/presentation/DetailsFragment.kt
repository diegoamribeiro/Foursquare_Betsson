package com.dmribeiro87.foursquarebetsson.detailsFeature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImage
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImageRounded
import com.dmribeiro87.foursquarebetsson.core.util.viewBinding
import com.dmribeiro87.foursquarebetsson.databinding.FragmentDetailsBinding
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.PlaceDetails
import com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.adapters.ImagesAdapter
import com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.adapters.TipAdapter
import com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: ImagesAdapter
    private lateinit var tipAdapter: TipAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setTipAdapter()
        viewModel.loadPlaceDetails(args.fsqId)
        addObserver()
    }

    private fun addObserver() {
        viewModel.details.observe(viewLifecycleOwner){ resource ->
            resource.let {
                when(resource){
                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        resource.data?.let { makeScreen(it) }
                        resource.data?.let { adapter.setData(it.photos) }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.tips.observe(viewLifecycleOwner){ resource ->
            resource.let {
                when(resource){
                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        resource.data?.let { tipAdapter.setData(it) }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun makeScreen(details: PlaceDetails) {
        binding.ivHeader.loadImage(details.photos[0], requireContext())
        binding.tvPlaceName.text = details.name
        binding.tvPlaceType.text = details.category
        binding.tvAddress.text = details.location
        binding.tvTel.text = details.tel
        binding.tvOpenUntil.text = details.hours
        binding.tvRating.text = details.rating.toString()
    }

    private fun setAdapter() {
        adapter = ImagesAdapter()
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPhoto.adapter = adapter
        adapter.setAction { image ->
            binding.ivHeader.loadImage(image, requireContext())
        }
    }

    private fun setTipAdapter() {
        tipAdapter = TipAdapter()
        binding.rvTips.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvTips.adapter = tipAdapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvTips.context,
            LinearLayoutManager.VERTICAL
        )

        binding.rvTips.addItemDecoration(dividerItemDecoration)

    }


}