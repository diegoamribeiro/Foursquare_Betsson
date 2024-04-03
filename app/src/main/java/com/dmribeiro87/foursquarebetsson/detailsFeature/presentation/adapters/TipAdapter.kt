package com.dmribeiro87.foursquarebetsson.detailsFeature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImage
import com.dmribeiro87.foursquarebetsson.core.util.Utils.loadImageRounded
import com.dmribeiro87.foursquarebetsson.core.util.Utils.toAmericanDateFormat
import com.dmribeiro87.foursquarebetsson.databinding.TipItemBinding
import com.dmribeiro87.foursquarebetsson.detailsFeature.domain.model.TipItem

class TipAdapter: RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

    private val tipList = mutableListOf<TipItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = TipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TipViewHolder(binding)
    }

    override fun getItemCount() = tipList.size

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(tipList[position], holder.itemView.context)
    }

    fun setData(tips: List<TipItem>){
        tipList.addAll(tips)
        notifyDataSetChanged()
    }

    inner class TipViewHolder(private val binding: TipItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tip: TipItem, context: Context) {
            binding.ivProfile.loadImageRounded(tip.photoUrl, context, 25)
            binding.tvUserName.text = "Unknown User"
            binding.tvTipDate.text = tip.createdAt.toAmericanDateFormat()
            binding.tvTipText.text = tip.text
        }
    }
}