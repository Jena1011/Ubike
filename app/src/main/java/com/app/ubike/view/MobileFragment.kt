package com.app.ubike.view

import android.graphics.Outline
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.ubike.R
import com.app.ubike.adapter.StationsAdapter
import com.app.ubike.databinding.FragmentMobileBinding
import com.app.ubike.viewmodel.MobileViewModel


/**
 * 用於顯示「站點資訊」畫面的 [Fragment] 子類
 */
class MobileFragment : Fragment() {

    private val TAG: String = "MobileFragment"
    private val viewModel: MobileViewModel by viewModels()
    lateinit var rvAdapter: StationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 載入視圖
        val binding = FragmentMobileBinding.inflate(inflater)

        // 雙向綁定
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // 站點列表區塊圓角裁切
        binding.llStations.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view?.width ?: 0, view?.height ?: 0, 30.0f)
            }
        }
        binding.llStations.clipToOutline = true

        // 站點列表 rv 設定 adapter
        rvAdapter = StationsAdapter(R.layout.item_layout_01)
        binding.rvStations.adapter = rvAdapter

        // 監聽 autoCompleteTextView 文字變化
        binding.autoCompleteTextView.doOnTextChanged { text, _, _, _ ->
            // 執行搜尋
            viewModel.queryStationsByKeyword(text.toString())
        }

        // 導航至「目錄」畫面
        binding.iconMenu.setOnClickListener {
            val action = MobileFragmentDirections.actionMobileFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}