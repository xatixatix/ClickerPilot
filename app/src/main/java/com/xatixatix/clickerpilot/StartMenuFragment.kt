package com.xatixatix.clickerpilot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.xatixatix.clickerpilot.data.Player
import com.xatixatix.clickerpilot.databinding.FragmentStartMenuBinding

class StartMenuFragment : Fragment() {
    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as ClickerPilotApplication).database.playerDao()
        )
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startGameAction.setOnClickListener {
            if (!viewModel.isPlayerExistsByName(binding.playerName.text.toString())) {
                val newPlayer = Player(
                        name = binding.playerName.text.toString(),
                        currencyAmount = 0,
                        playTime = 0
                    )
                viewModel.createPlayer(newPlayer)
                viewModel.currentPlayer = newPlayer
            } else {
                Log.i("Player creation", "Player is already created, no action needed.")
            }
            val action = StartMenuFragmentDirections.actionStartMenuFragmentToBaseGameFragment()
            findNavController().navigate(action)
        }
    }
}