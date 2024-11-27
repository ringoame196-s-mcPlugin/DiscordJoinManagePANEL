package com.github.ringoame196_s_mcPlugin.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinEvent : Listener {
	@EventHandler
	fun onPlayerJoin(e: PlayerJoinEvent) {
		val player = e.player
	}
}
