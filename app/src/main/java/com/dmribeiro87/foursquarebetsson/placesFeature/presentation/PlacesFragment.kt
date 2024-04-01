package com.dmribeiro87.foursquarebetsson.placesFeature.presentation

import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dmribeiro87.foursquarebetsson.R
import com.dmribeiro87.foursquarebetsson.core.util.Resource
import com.dmribeiro87.foursquarebetsson.core.util.Utils.hide
import com.dmribeiro87.foursquarebetsson.core.util.Utils.show
import com.dmribeiro87.foursquarebetsson.core.util.viewBinding
import com.dmribeiro87.foursquarebetsson.databinding.FragmentPlacesBinding
import com.dmribeiro87.foursquarebetsson.placesFeature.presentation.viewModel.PlacesViewModel
import com.dmribeiro87.foursquarebetsson.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlacesFragment : Fragment() {

    private val binding: FragmentPlacesBinding by viewBinding()
    private val viewModel : PlacesViewModel by viewModels()
    private lateinit var adapter: PlacesAdapter
    private var isOpen = false
    private var selectedItem = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(requireContext(), R.color.toolbar_color))
        )
        binding.clTop.setBackgroundColor(
            ResourcesCompat.getColor(resources, R.color.toolbar_color, requireContext().theme)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setAdapter()
        addObserver()
        setListener()

        viewModel.searchPlaces()
    }

    private fun addObserver() {
        viewModel.placesData.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is Resource.Loading -> {
                    binding.pbLoading.show()
                    binding.rvMain.hide()
                }
                is Resource.Success -> {
                    binding.pbLoading.hide()
                    binding.rvMain.show()
                    Log.d("***Fragment", resource.data.toString())
                    resource.data?.let { adapter.setData(it) }
                }
                is Resource.Error -> {
                    binding.pbLoading.hide()
                    binding.rvMain.hide()
                    Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setListener() {
        binding.swOpen.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateFilters(selectedItem, isChecked)
        }
    }

    private fun setAdapter() {
        adapter = PlacesAdapter()
        binding.rvMain.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvMain.adapter = adapter
    }

    private fun setupSpinner() {
        val spinner = binding.spinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position) as String
                viewModel.updateFilters(selectedItem, isOpen)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // DO Nothing
            }
        }

        val items = resources.getStringArray(R.array.spinner_items)
        val spinnerAdapter = object : ArrayAdapter<String>(requireContext(), R.layout.spinner_custom_layout, R.id.spinner_text, items) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_item, parent, false)
                val textView = view.findViewById<TextView>(R.id.spinner_item_text)

                val text = SpannableString("$$$$").apply {
                    val grayColor = ContextCompat.getColor(requireContext(), R.color.light_gray)
                    val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                    for (i in indices) {
                        val colorSpan = if (i < position + 1) ForegroundColorSpan(blackColor) else ForegroundColorSpan(grayColor)
                        setSpan(colorSpan, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        val styleSpan = if (i < position + 1) StyleSpan(Typeface.BOLD) else StyleSpan(
                            Typeface.NORMAL)
                        setSpan(styleSpan, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                textView.text = text
                return view
            }
        }
        spinner.adapter = spinnerAdapter
    }
}