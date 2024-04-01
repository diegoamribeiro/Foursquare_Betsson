package com.dmribeiro87.foursquarebetsson.placesFeature.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dmribeiro87.foursquarebetsson.R
import com.dmribeiro87.foursquarebetsson.core.util.DiffUtilGeneric
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImage
import com.dmribeiro87.foursquarebetsson.databinding.PlaceItemBinding
import com.dmribeiro87.foursquarebetsson.placesFeature.domain.model.Place

class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    private var placesList = emptyList<Place>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacesViewHolder(binding)
    }

    override fun getItemCount() = placesList.size

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(placesList[position], holder.itemView.context)
    }

    fun setData(list: List<Place>) {
        val placeUtil = DiffUtilGeneric(placesList, list)
        val listResult = DiffUtil.calculateDiff(placeUtil)
        this.placesList = list
        listResult.dispatchUpdatesTo(this)
    }

    inner class PlacesViewHolder(private val binding: PlaceItemBinding) : ViewHolder(binding.root) {
        fun bind(place: Place, context: Context) {
            val distanceInKilometers = place.distance / 1000.0
            val currencySign = "$"
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_flavor))
            binding.tvPlaceTitle.text = place.name
            binding.tvDistance.text = String.format("%.1fKM", distanceInKilometers)
            binding.tvScore.text = place.rating.toString()
            binding.tvPrice.text = place.price?.let { currencySign.repeat(it) }

            // Verifica se a lista de fotos não está vazia antes de tentar acessar o primeiro item
            val photoUrl = if (place.photos?.isNotEmpty() == true) {
                place.photos[0].prefix + "300" + place.photos[0].suffix
            } else {
                "" // Forneça uma URL de imagem padrão ou deixe em branco
            }
            binding.ivMain.loadImage(photoUrl, context)
        }
    }


}