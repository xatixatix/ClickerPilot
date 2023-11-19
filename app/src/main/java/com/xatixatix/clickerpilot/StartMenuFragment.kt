package com.xatixatix.clickerpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xatixatix.clickerpilot.databinding.FragmentStartMenuBinding

class StartMenuFragment : Fragment() {
    private var _binding: FragmentStartMenuBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartMenuBinding.inflate(inflater, container, false)
        return binding.root
    }
}