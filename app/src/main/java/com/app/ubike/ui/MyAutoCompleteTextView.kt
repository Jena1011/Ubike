package com.app.ubike.ui

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.doOnTextChanged
import com.app.ubike.R
import com.app.ubike.adapter.ContainsFilterAdapter

/**
 * 自訂 UI 元件，繼承 AppCompatAutoCompleteTextView 子類，支援 dataBinding、完成建議使用 contain filter、自訂風格
 */
class MyAutoCompleteTextView : androidx.appcompat.widget.AppCompatAutoCompleteTextView {

    val TAG = "MyAutoCompleteTextView"
    private var _strList: MutableList<String> = mutableListOf()
    private var strList: MutableList<String>
        get() = _strList
        set(value) {
            _strList = value
        }
    var currentText: String = ""
    private val adapter:ContainsFilterAdapter
        get() = ContainsFilterAdapter(context,strList)

    /**
     * 建構子
     */
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    /**
     * 建構子
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    /**
     * 建構子
     */
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    /**
     * 初始化
     */
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.MyAutoCompleteTextView, defStyle, 0
        )

        // 從 StyledAttrs 取得數據陣列，設定為 strList 的值
        val simpleItemsResId = a.getResourceId(R.styleable.MyAutoCompleteTextView_dropdownItems, 0)
        if (simpleItemsResId != 0) {
            _strList = resources.getStringArray(simpleItemsResId).toList() as MutableList<String>
        }

        // 設定適配器 ContainsFilterAdapter
        setAdapter(adapter)

        // 監聽 autoCompleteTextView 文字變化
       doOnTextChanged { text, _, _, _ ->

            // 獲取當前輸入文字
            currentText = text.toString()

            // 修改 search icon 顏色
            if (text != null) {
                if (text.isNotEmpty()) {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_search_green,
                        0
                    )
                } else {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_search,
                        0
                    )
                }
            }
        }
        a.recycle()
    }

    /**
     * 設定建議列表內容
      */
    fun submitList(data: List<String>?) {
        _strList.clear()
        if (data != null) {
            _strList.addAll(data)
        }
        adapter.submitList(data)
    }
}