package com.app.ubike.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.adapter.StationsAdapter
import com.app.ubike.network.Station

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Station>?) {
    val adapter = recyclerView.adapter as StationsAdapter
    adapter.submitList(data)
}