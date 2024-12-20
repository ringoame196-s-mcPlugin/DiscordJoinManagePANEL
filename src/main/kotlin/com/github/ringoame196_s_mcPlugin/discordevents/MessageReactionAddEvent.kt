package com.github.ringoame196_s_mcPlugin.discordevents

import com.github.ringoame196_s_mcPlugin.managers.DataManager
import com.github.ringoame196_s_mcPlugin.managers.PanelManager
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class MessageReactionAddEvent(private val plugin: Plugin) : ListenerAdapter() {
    private val panelManager = PanelManager()

    override fun onMessageReactionAdd(e: MessageReactionAddEvent) {
        val user = e.user ?: return
        val member = e.guild.getMember(user) ?: return
        val emoji = e.reactionEmote.emoji
        val messageID = e.messageId
        if (user.isBot) return
        if (!isExecutable(member)) return

        e.textChannel.retrieveMessageById(messageID).queue { message ->
            // embedじゃなければ 処理を終わる
            if (message.embeds.isEmpty()) return@queue
            val userMention = "<@${user.id}>"
            val embed = message.embeds[0]
            val playerName = embed.author?.name ?: return@queue
            val player = Bukkit.getPlayer(playerName)
            val panelData = DataManager.acquisitionReactionPanelData(emoji)

            if (!panelManager.isPanel(embed)) return@queue
            val sendMessage: String
            if (panelData == null) {
                sendMessage = "${emoji}のコマンドは未設定です"
            } else {
                if (player != null) {
                    val lore = panelData.lore
                    val command = panelData.command
                    panelManager.runMcCommand(plugin, command, player)
                    sendMessage = "${player.name}に「$lore」を実行しました"
                } else {
                    sendMessage =
                        "${playerName}の取得に失敗しました(オフラインの場合は取得できません)"
                }
            }
            message.reply("$userMention\n$sendMessage").queue()
            e.reaction.removeReaction(user).queue()
        }
    }

    private fun isExecutable(member: Member): Boolean {
        return member.hasPermission(Permission.ADMINISTRATOR)
    }
}
