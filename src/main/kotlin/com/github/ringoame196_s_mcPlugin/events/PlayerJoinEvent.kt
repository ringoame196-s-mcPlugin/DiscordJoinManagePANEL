package com.github.ringoame196_s_mcPlugin.events

import com.github.ringoame196_s_mcPlugin.Data
import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinEvent : Listener {
    private val panelManager = PanelManager()

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        if (Data.JDA == null) return // bot起動を前提に
        val player = e.player
        panelManager.send(player)
    }
}
