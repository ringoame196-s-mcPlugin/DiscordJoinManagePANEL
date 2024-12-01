package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.awt.Color
import java.io.File

class PanelManager {
	private val discordBotManager = DiscordBotManager()

	fun loadPanel(plugin: Plugin) {
		val file = File(plugin.dataFolder, "reactionPanel.yml")
		val ymlFile = YamlConfiguration.loadConfiguration(file)

		for (key in ymlFile.getKeys(true)) {
			val lore = ymlFile.getString("$key.lore") ?: continue
			val command = ymlFile.getString("$key.command") ?: continue
			DataManager.addReaction(key, lore, command)
		}
	}

	fun send(player: Player) {
		val displayChannelID = Data.displayChannelID ?: return
		val channel = discordBotManager.acquisitionTextChannel(displayChannelID) ?: return
		val color = Color.GREEN
		val joinMessage = "サーバーに参加しました"
		val message = "処理をクリック"
		val reactions = DataManager.acquisitionReactions()
		val reactionInfo = mutableListOf<MessageEmbed.Field>()
		for ((reactionID, reactionData) in reactions) {
			reactionInfo.add(MessageEmbed.Field(reactionID, reactionData.lore, true))
		}

		val embed =
			discordBotManager.makeEmbed(
				title = joinMessage,
				descriptor = message,
				color = color,
				fields = reactionInfo,
				player = player
			)
		channel.sendMessageEmbeds(embed).queue { message: Message ->
			// リアクションをつける
			for (reaction in DataManager.acquisitionReactions()) {
				val emoji = reaction.key
				message.addReaction(emoji).queue()
			}
		}
	}

	fun runCommand(reactionID: String, player: Player) {}
}
