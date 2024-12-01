package com.github.ringoame196_s_mcPlugin.commands

import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

class Command(private val plugin: Plugin) : CommandExecutor {
    private val panelManager = PanelManager()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false
        val subCommand = args[0]

        when (subCommand) {
            CommandConst.RELOAD_COMMAND -> reloadCommand(sender)
            else -> {
                val message = "${ChatColor.RED}構文が間違っています"
                sender.sendMessage(message)
            }
        }

        return true
    }

    private fun reloadCommand(sender: CommandSender) {
        val message = "${ChatColor.AQUA}パネルをリロードしました"
        panelManager.loadPanel(plugin)
        sender.sendMessage(message)
    }
}
