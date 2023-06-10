package com.app.ubike.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.ubike.BR
import com.app.ubike.network.Station

/**
 * 一個適用於 dataBinding 用來在 RecyclerView 顯示站點資料的 ListAdapter
 * @param layoutResId 列表項布局資源 id
 */
class StationsAdapter(private val layoutResId: Int) :
    ListAdapter<Station, StationsAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        // 將資料綁定到 binding 的 station 變數上
        fun bind(station: Station) {
            binding.setVariable(BR.station, station) // BR.station 是在佈局檔案中定義的變數名稱
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // 綁定數據資料
        val station = getItem(position)
        holder.bind(station as Station)

        // 奇偶行顏色不同
        if (position%2==0){
            holder.itemView.setBackgroundColor(Color.argb(1, 246, 246, 246)) // 淺灰
        }else{
            holder.itemView.setBackgroundColor(Color.argb(255, 246, 246, 246))  //深灰
        }
    }
}

/**
 * 定義 DiffCallback 用於判斷新舊資料是否相同
 */
object DiffCallback: DiffUtil.ItemCallback<Station>() {

    // 判斷兩個項目是否相同
    override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.sna == newItem.sna
    }

    // 判斷兩個項目的內容是否相同
    override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
        return oldItem.sarea == newItem.sarea
    }

}
