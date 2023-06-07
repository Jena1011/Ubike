package com.app.ubike.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            adviceAdapter = ContainsFilterAdapter(requireContext(),adviceList)
            binding.autoCompleteTextView.setAdapter(adviceAdapter)

            Log.d(TAG,adviceList.toString())
            Log.d(TAG,adviceAdapter.count.toString())

        }

        return binding.root
    }
}