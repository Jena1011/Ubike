package com.app.ubike.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.R

class StationsAdapter : RecyclerView.Adapter<StationsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
         val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_01, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // 奇偶行顏色不同
        if (position%2==0){
            holder.itemView.setBackgroundColor(Color.argb(1, 246, 246, 246))
        }else{
            holder.itemView.setBackgroundColor(Color.argb(255, 246, 246, 246))
        }
    }


    override fun getItemCount(): Int {
        return 20
    }

}
