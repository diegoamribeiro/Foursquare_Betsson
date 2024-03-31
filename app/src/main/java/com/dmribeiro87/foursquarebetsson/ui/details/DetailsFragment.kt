package com.dmribeiro87.foursquarebetsson.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmribeiro87.foursquarebetsson.R
import com.dmribeiro87.foursquarebetsson.core.util.viewBinding
import com.dmribeiro87.foursquarebetsson.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


}