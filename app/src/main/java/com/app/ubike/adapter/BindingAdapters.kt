package com.app.ubike.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.network.Station
import com.app.ubike.ui.MyAutoCompleteTextView

/**
 * 這個方法用來將資料綁定到 RecyclerView 上，並更新 RecyclerView 的內容。
 */
@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Station>?
) {
    val adapter = recyclerView.adapter as StationsAdapter
    adapter.submitList(data)
}

/**
 * 這個方法用來將資料綁定到 AutoCompleteTextView 上，並更新 DropDown 的內容。
 */
@BindingAdapter("dropdownItems")
fun bindAutoCompleteTextView(autoCompleteTextView: MyAutoCompleteTextView, data: List<String>?) {
    if (data != null) {
        autoCompleteTextView.submitList(data)
    }
}