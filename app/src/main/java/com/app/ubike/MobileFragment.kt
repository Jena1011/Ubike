package com.app.ubike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.ubike.databinding.FragmentMobileBinding

class MobileFragment : Fragment() {

    private lateinit var viewModel: MobileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMobileBinding.inflate(inflater)
        return binding.root
    }


}