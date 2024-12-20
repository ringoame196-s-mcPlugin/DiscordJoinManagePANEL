package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.discordevents.MessageReactionAddEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.TextChannel
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.awt.Color
import java.time.temporal.TemporalAccessor

class DiscordBotManager {
    fun boot(plugin: Plugin) {
        val config = plugin.config
        val token = config.getString("token")
        val activityText = config.getString("activity") ?: "Minecraft"

        try {
            if (token != "" && token != null) { // tokenが設定されていないと 実行しない
                val jdaBuilder = JDABuilder.createDefault(token)
                jdaBuilder.setActivity(Activity.playing(activityText)) // アクティビティ登録
                val jda = jdaBuilder.addEventListeners(MessageReactionAddEvent(plugin)).build() // bot起動
                jda.awaitReady()
                DataManager.setJDA(jda)
            } else {
                val message = "[${plugin.name}] token not set"
                println(message) // token未設定時にメッセージを出す
            }
        } catch (e: Exception) {
            val errorMessage = "BOT起動中にエラーが発生しました。 ${e.message}"
            println(errorMessage) // エラー文出力
        }
    }

    fun makeEmbed(
        title: String,
        titleURL: String? = null,
        color: Color? = null,
        descriptor: String? = null,
        image: String? = null,
        player: Player? = null,
        footer: String? = null,
        thumbnail: String? = null,
        timestamp: TemporalAccessor? = null,
        fields: MutableList<MessageEmbed.Field>? = null
    ): MessageEmbed {
        val embed = EmbedBuilder()
        embed.setTitle(title, titleURL) // タイトル
        embed.setDescription(descriptor) // 説明
        embed.setColor(color) // カラー
        embed.setImage(image) // 画像
        embed.setFooter(footer) // フッター
        embed.setThumbnail(thumbnail) // サムネ
        embed.setTimestamp(timestamp) // タイムスタンプ

        if (fields != null) { // フィールドを追加
            for (field in fields) {
                embed.addField(field)
            }
        }

        if (player != null) {
            val playerName = player.name
            val playerUUID = player.uniqueId.toString()
            val iconURL: String = DataManager.acquisitionIconAPI().replace("@uuid", playerUUID)
            embed.setAuthor(playerName, null, iconURL) // author
        }

        return embed.build()
    }

    fun acquisitionTextChannel(channelID: String): TextChannel? {
        val jda = DataManager.acquisitionJDA() ?: return null
        return jda.getTextChannelById(channelID)
    }
}
