package com.xatixatix.clickerpilot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xatixatix.clickerpilot.databinding.FragmentPlayersPageBinding

class PlayersPageFragment : Fragment() {
    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as ClickerPilotApplication).database.playerDao()
        )
    }

    private var _binding: FragmentPlayersPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayersPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlayerListAdapter {
            val action =
                PlayersPageFragmentDirections.actionPlayersPageFragmentToBaseGameFragment()
            this.findNavController().navigate(action)
        }
        binding.playerRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.playerRecyclerView.adapter = adapter
        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.
        viewModel.players.observe(this.viewLifecycleOwner) { players ->
            players.let {
                adapter.submitList(it)
            }
        }
    }
}