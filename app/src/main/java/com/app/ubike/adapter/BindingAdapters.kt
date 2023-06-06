package com.app.ubike.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.network.Station

/**
 * 這個方法用來將資料綁定到 RecyclerView 上，並更新 RecyclerView 的內容。
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Station>?) {
    val adapter = recyclerView.adapter as StationsAdapter
    adapter.submitList(data)
}