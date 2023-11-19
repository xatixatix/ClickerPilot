package com.xatixatix.clickerpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xatixatix.clickerpilot.databinding.FragmentBaseGameBinding

class BaseGameFragment : Fragment() {
    private var _binding: FragmentBaseGameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseGameBinding.inflate(inflater, container, false)
        return binding.root
    }
}