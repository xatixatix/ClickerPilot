package com.xatixatix.clickerpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xatixatix.clickerpilot.databinding.FragmentStatsPageBinding

class StatsPageFragment : Fragment() {
    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as ClickerPilotApplication).database.playerDao()
        )
    }

    private var _binding: FragmentStatsPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}
