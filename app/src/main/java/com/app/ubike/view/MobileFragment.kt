package com.app.ubike.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.ubike.viewmodel.MobileViewModel
import com.app.ubike.adapter.StationsAdapter
import com.app.ubike.databinding.FragmentMobileBinding

class MobileFragment : Fragment() {

    private val viewModel: MobileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMobileBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvStations.adapter = StationsAdapter()

        return binding.root
    }


}