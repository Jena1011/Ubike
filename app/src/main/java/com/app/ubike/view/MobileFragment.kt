package com.app.ubike.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.ubike.R
import com.app.ubike.adapter.ContainsFilterAdapter
import com.app.ubike.adapter.StationsAdapter
import com.app.ubike.databinding.FragmentMobileBinding
import com.app.ubike.network.Station
import com.app.ubike.viewmodel.MobileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * 用於顯示「站點資訊」畫面的 [Fragment] 子類
 */
class MobileFragment : Fragment() {

    private val TAG: String = "MobileFragment"
    private val viewModel: MobileViewModel by viewModels()
    lateinit var adapter: StationsAdapter
    private var adviceList: MutableList<String> = mutableListOf()
    private lateinit var adviceAdapter: ContainsFilterAdapter
    var currentText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 載入視圖
        val binding = FragmentMobileBinding.inflate(inflater)

        // 雙向綁定
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // 站點列表 rv 設定 adapter
        adapter = StationsAdapter()
        binding.rvStations.adapter = adapter

        // 設定 autoCompleteTextView 自動完成建議
        CoroutineScope(Dispatchers.Main).launch {

            val data = viewModel.fetchStations()
            data.forEach { station: Station -> adviceList.add(station.sna) }
            adviceAdapter = ContainsFilterAdapter(requireContext(), adviceList)
            binding.autoCompleteTextView.setAdapter(adviceAdapter)

            Log.d(TAG, adviceList.toString())
            Log.d(TAG, adviceAdapter.count.toString())

        }

        // 監聽 autoCompleteTextView 文字變化
        binding.autoCompleteTextView.doOnTextChanged { text, _, _, _ ->

            // 獲取當前輸入文字
            currentText = text.toString()

            // 修改 search icon 顏色
            if (text != null) {
                if (text.isNotEmpty()) {
                    binding.autoCompleteTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_search_green,
                        0
                    )
                } else {
                    binding.autoCompleteTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_search,
                        0
                    )
                }
            }
        }

        // 監聽 IME 事件
        binding.autoCompleteTextView.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE, EditorInfo.IME_ACTION_SEARCH -> {

                    // 隱藏鍵盤
                    val imm =  requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.autoCompleteTextView.windowToken, 0)

                    // 執行搜尋
                    viewModel.queryStationsByKeyword(currentText)

                    true
                }
                else -> false
            }
        }

        return binding.root
    }
}