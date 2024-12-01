package com.github.ringoame196_s_mcPlugin

import com.github.ringoame196_s_mcPlugin.commands.Command
import com.github.ringoame196_s_mcPlugin.commands.TabCompleter
import com.github.ringoame196_s_mcPlugin.events.PlayerJoinEvent
import com.github.ringoame196_s_mcPlugin.managers.DiscordBotManager
import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    private val plugin = this
    private val discordBotManager = DiscordBotManager()

    override fun onEnable() {
        super.onEnable()
        saveDefaultConfig()
        saveResource("reactionPanel.yml", false)
        val config = plugin.config
        Data.displayChannelID = config.getString("display_channel_id")
        Data.permitRoll = config.getString("permit_roll_id")

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
