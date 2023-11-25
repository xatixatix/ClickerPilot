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
    private val players: LiveData<List<Player>> = playerDao.getPlayerList().asLiveData()

    fun isPlayerExistsByName(name: String): Boolean {
        for (player in players.value!!) {
            if (player.name == name) {
                return true
            }
        }
        return false
    }

    fun createPlayer(player: Player) {
        viewModelScope.launch {
            playerDao.insert(player)
        }
        Log.i("Player creation", "New player created.")
    }

    fun updatePlayer(
        playerId: Int,
        playerName: String,
        playerCurrencyAmount: Long,
        playerPlayTime: Long
    ) {
        val updatedPlayer = Player(
            playerId,
            playerName,
            playerCurrencyAmount,
            playerPlayTime
        )
        updatePlayer(updatedPlayer)
    }

    private fun updatePlayer(player: Player) {
        viewModelScope.launch {
            playerDao.update(player)
        }
    }

    fun getPlayerByName(name: String): LiveData<Player> {
        return playerDao.getPlayer(name).asLiveData()
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