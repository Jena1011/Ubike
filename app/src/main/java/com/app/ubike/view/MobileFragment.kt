package com.app.ubike.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.ubike.adapter.StationsAdapter
import com.app.ubike.databinding.FragmentMobileBinding
import com.app.ubike.viewmodel.MobileViewModel

/**
 * 用於顯示「站點資訊」畫面的 [Fragment] 子類
 */
class MobileFragment : Fragment() {

    private val viewModel: MobileViewModel by viewModels()
    lateinit var adapter: StationsAdapter

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

        return binding.root
    }
}