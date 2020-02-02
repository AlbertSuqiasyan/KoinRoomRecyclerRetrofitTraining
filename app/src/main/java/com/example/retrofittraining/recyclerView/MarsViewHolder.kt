package com.example.retrofittraining.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.data.MarsData
import com.example.retrofittraining.databinding.ListItemMarsBinding
import com.squareup.picasso.Picasso

class MarsViewHolder private constructor(private val binding: ListItemMarsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: MarsData,
        clickListener: ClickListener
    ) {
        binding.tvPrice.text = item.price.toString()
        binding.tvType.text = item.type
        binding.clickListener = clickListener
        binding.mars = item
        Picasso.with(itemView.context)
            .load(item.img_src)
            .into(binding.marsImage)
    }

    companion object {
        fun from(parent: ViewGroup): MarsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemMarsBinding.inflate(layoutInflater, parent, false)
            return MarsViewHolder(binding)
        }
    }
}