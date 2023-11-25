package com.xatixatix.clickerpilot.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "currencyAmount")
    var currencyAmount: Long,
    @ColumnInfo(name = "playTime")
    val playTime: Long
)