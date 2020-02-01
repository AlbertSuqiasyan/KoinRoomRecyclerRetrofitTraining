package com.example.retrofittraining.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.data.MarsData

class MarsAdapter(val clickListener: ClickListener) : ListAdapter<MarsData,MarsViewHolder>(MarsDiffCallBack()) {

    var data = listOf<MarsData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        return MarsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class MarsDiffCallBack : DiffUtil.ItemCallback<MarsData>() {
    override fun areItemsTheSame(oldItem: MarsData, newItem: MarsData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarsData, newItem: MarsData): Boolean {
        return oldItem == newItem
    }
}


