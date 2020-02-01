package com.example.retrofittraining.recyclerView

import com.example.retrofittraining.data.MarsData

class ClickListener (val clickListener: (marsId: String) -> Unit) {
    fun onClick(marsProperty: MarsData) = clickListener(marsProperty.id)
}