package com.xatixatix.clickerpilot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xatixatix.clickerpilot.data.Player
import com.xatixatix.clickerpilot.databinding.PlayerListItemBinding

class PlayerListAdapter(private val onItemClicked: (Player) -> Unit) :
    ListAdapter<Player, PlayerListAdapter.PlayerViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class PlayerViewHolder(private var binding: PlayerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.playerName.text = player.name
            binding.playerCurrencyAmount.text = player.currencyAmount.toString()
            binding.playerPlaytime.text = player.playTime.toString()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}

