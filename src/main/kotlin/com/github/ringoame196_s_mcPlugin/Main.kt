package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.events.PlayerJoinEvent
import com.github.ringoame196_s_mcPlugin.managers.DiscordBotManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
	private val plugin = this
	private val discordBotManager = DiscordBotManager()

	override fun onEnable() {
		super.onEnable()
		saveDefaultConfig()
		discordBotManager.boot(plugin) // DiscordBOT起動させる

		server.pluginManager.registerEvents(PlayerJoinEvent(), plugin)
		val command = getCommand("djoinpmanager")
		command!!.setExecutor(Command(plugin))
		command.tabCompleter = TabCompleter()
	}
}
