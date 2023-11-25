package com.xatixatix.clickerpilot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xatixatix.clickerpilot.data.Player
import com.xatixatix.clickerpilot.data.PlayerDao
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerDao: PlayerDao) : ViewModel() {
    var players: LiveData<List<Player>> = playerDao.getPlayerList().asLiveData()
    var currentPlayer: Player = Player(0, "empty", 0, 0)

    fun isPlayerExistsByName(name: String): Boolean {
        Log.i("Players", players.value.toString())
        if (players.value?.isNotEmpty() == true) {
            for (player in players.value!!) {
                if (player.name == name) {
                    currentPlayer = player
                    return true
                }
            }
        }
        return false
    }

    fun createPlayer(player: Player) {
        viewModelScope.launch {
            playerDao.insert(player)
        }
        currentPlayer = player
        Log.i("Player creation", "New player created.")
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch {
            playerDao.update(player)
        }
    }
}

class PlayerViewModelFactory(private val playerDao: PlayerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(playerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}