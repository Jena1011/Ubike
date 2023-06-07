package com.app.ubike.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Filter
import android.widget.TextView
import com.app.ubike.R

/**
 * [ArrayAdapter] 子類，用來提供 [AutoCompleteTextView] 自動完成建議下拉選單內容，提供 item 佈局及自訂過濾功能
 */
class ContainsFilterAdapter(context: Context, private val originalList: List<String>): ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line) {

    var mFilteredList = mutableListOf<String>() // 過濾後的資料列表

    /**
     * 獲取過濾後的資料列表筆數
     * @return 列表筆數
     */
    override fun getCount(): Int {
        return mFilteredList.size
    }

    /**
     * 獲取過濾後的資料列表中指定位置的項目
     * @param position 資料位置
     */
    override fun getItem(position: Int): String {
        return mFilteredList[position]
    }

    /**
     * 獲取過濾器對象，以執行過濾操作
     * @return Filter
     */
    override fun getFilter(): Filter {
        return object : Filter() {

            /**
             * 執行過濾操作，找出包含關鍵字的資料，將結果存入 [mFilteredList] 中
             */
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                mFilteredList.clear()

                constraint?.let { searchText ->
                    for (item in originalList) {
                        if (item.contains(searchText, ignoreCase = true)) {
                            mFilteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = mFilteredList
                results.count = mFilteredList.size

                return results
            }

            /**
             * 發布過濾結果，將過濾後的資料列表 [mFilteredList] 加入 Adapter 中並通知數據集已更改
             */
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is List<*>) {
                    addAll(mFilteredList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    /**
     * 獲取指定位置的項目視圖，並設置顯示內容
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        super.getView(position, convertView, parent)

        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_dropdown_item, parent, false)
        }

        val itemText = view?.findViewById<TextView>(R.id.itemText)
        itemText?.text = getItem(position)

        return view!!
    }

}