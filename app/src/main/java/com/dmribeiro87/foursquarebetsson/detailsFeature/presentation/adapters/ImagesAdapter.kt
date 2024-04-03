package com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImageRounded
import com.dmribeiro87.foursquarebetsson.databinding.ImageItemBinding

class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private val imageList = mutableListOf<String>()

    private var action: ((String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, action)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position], holder.itemView.context)
    }

    fun setData(images: List<String>){
        imageList.addAll(images)
        notifyDataSetChanged()
    }

    fun setAction(action: (String) -> Unit){
        this.action = action
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding, private val action: ((String) -> Unit)?): RecyclerView.ViewHolder(binding.root){
        fun bind(image: String, context: Context) {
            binding.ivSpinner.loadImageRounded(image, context, 16)
            binding.root.setOnClickListener { action?.invoke(image) }
        }
    }
}