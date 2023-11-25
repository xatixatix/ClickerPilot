package com.xatixatix.clickerpilot.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * from player ORDER BY name ASC")
    fun getPlayerList(): Flow<List<Player>>

    @Query("SELECT * from player WHERE id = :id")
    fun getPlayer(id: Int): Flow<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)
}
