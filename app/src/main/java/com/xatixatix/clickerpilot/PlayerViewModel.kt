package com.xatixatix.clickerpilot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.xatixatix.clickerpilot.data.Player
import com.xatixatix.clickerpilot.data.PlayerDao

class PlayerViewModel(private val playerDao: PlayerDao) : ViewModel() {
    val players: LiveData<List<Player>> = playerDao.getPlayerList().asLiveData()

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