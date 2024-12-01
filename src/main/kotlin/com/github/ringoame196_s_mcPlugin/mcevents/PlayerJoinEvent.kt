package com.github.ringoame196_s_mcPlugin.mcevents

import com.github.ringoame196_s_mcPlugin.managers.DataManager
import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinEvent : Listener {
    private val panelManager = PanelManager()

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        DataManager.acquisitionJDA() ?: return // bot起動を前提に
        val player = e.player
        panelManager.sendPanelEmbed(player)
    }
}
