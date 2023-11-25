package com.xatixatix.clickerpilot

import android.app.Application
import com.xatixatix.clickerpilot.data.PlayerDatabase

class ClickerPilotApplication : Application() {
    val database: PlayerDatabase by lazy { PlayerDatabase.getDatabase(this) }
}