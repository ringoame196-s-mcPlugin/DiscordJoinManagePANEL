package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.managers.DataManager
import com.github.ringoame196_s_mcPlugin.managers.DiscordBotManager
import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import com.github.ringoame196_s_mcPlugin.mcevents.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
	private val plugin = this
	private val discordBotManager = DiscordBotManager()

	override fun onEnable() {
		super.onEnable()
		// ファイル生成
		saveDefaultConfig()
		saveResource("reactionPanel.yml", false)

		// config
		val config = plugin.config
		DataManager.setDisplayChannelID(config.getString("display_channel_id"))
		DataManager.setPermitRollID(config.getString("permit_roll_id"))

		discordBotManager.boot(plugin) // DiscordBOT起動させる

		// パネル関係
		val panelManager = PanelManager()
		panelManager.loadPanel(plugin)

		server.pluginManager.registerEvents(PlayerJoinEvent(), plugin)

		// マイクラコマンド
		val command = getCommand("djoinpmanager")
		command!!.setExecutor(Command(plugin))
		command.tabCompleter = TabCompleter()
	}
}
