package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data
import net.dv8tion.jda.api.entities.MessageEmbed
import org.bukkit.entity.Player
import java.awt.Color

class PanelManager {
	private val discordBotManager = DiscordBotManager()

	fun send(player: Player) {
		val displayChannelID = Data.displayChannelID ?: return
		val channel = discordBotManager.acquisitionTextChannel(displayChannelID) ?: return
		val title = "参加通知"
		val color = Color.GREEN
		val joinMessage = "${player.name}さんが参加しました"
		val playerInfo = mutableListOf(
			MessageEmbed.Field("uuid", player.uniqueId.toString(), false)
		)

		val embed = discordBotManager.makeEmbed(title = title, color = color, descriptor = joinMessage)
	}

	fun runCommand(reactionID: String, player: Player) {}
}