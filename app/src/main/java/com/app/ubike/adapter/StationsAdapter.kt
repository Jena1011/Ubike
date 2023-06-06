package com.app.ubike.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.databinding.ItemLayout01Binding
import com.app.ubike.network.Station

class StationsAdapter : ListAdapter<Station, StationsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: ItemLayout01Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(station: Station){
            binding.station = station
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemLayout01Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = getItem(position)
        holder.bind(station)
        // 奇偶行顏色不同
        if (position%2==0){
            holder.itemView.setBackgroundColor(Color.argb(1, 246, 246, 246))
        }else{
            holder.itemView.setBackgroundColor(Color.argb(255, 246, 246, 246))
        }


    }

}

object DiffCallback: DiffUtil.ItemCallback<Station>() {
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.sarea == newItem.sarea
    }

    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.sna == newItem.sna
    }

}
