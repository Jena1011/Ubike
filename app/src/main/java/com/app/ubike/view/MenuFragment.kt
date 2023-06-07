package com.app.ubike.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.ubike.databinding.FragmentMenuBinding

/**
 * 用於顯示「選單」畫面的 [Fragment] 子類
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 膨脹視圖
        val binding = FragmentMenuBinding.inflate(inflater)

        // 導航至「站點資訊」畫面
        val action = MenuFragmentDirections.actionMenuFragmentToMobileFragment()
        binding.tvStations.setOnClickListener {
            findNavController().navigate(action)
        }
        binding.iconClose.setOnClickListener {
            findNavController().navigate(action)
        }

        return binding.root
    }
}