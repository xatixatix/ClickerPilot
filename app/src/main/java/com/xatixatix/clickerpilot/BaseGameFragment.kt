package com.xatixatix.clickerpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xatixatix.clickerpilot.databinding.FragmentBaseGameBinding

class BaseGameFragment : Fragment() {
    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as ClickerPilotApplication).database.playerDao()
        )
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actionBaseGameFragmentToStatsPageFragment.setOnClickListener {
            val action = BaseGameFragmentDirections.actionBaseGameFragmentToStatsPageFragment();
            findNavController().navigate(action)
        }

        binding.clickToGetCurrency.setOnClickListener {
            //TODO: add +1 to currency
        }
    }
}