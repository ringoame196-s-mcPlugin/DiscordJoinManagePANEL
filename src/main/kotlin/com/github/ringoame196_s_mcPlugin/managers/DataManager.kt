package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.datas.Data
import com.github.ringoame196_s_mcPlugin.datas.PanelData
import net.dv8tion.jda.api.JDA

object DataManager {
    fun setJDA(JDA: JDA) {
        Data.JDA = JDA
    }

    fun acquisitionJDA() = Data.JDA

    fun setDisplayChannelID(discordChannelID: String?) {
        Data.displayChannelID = discordChannelID
    }

    fun acquisitionDisplayChannelID() = Data.displayChannelID

    fun addReaction(panelID: String, lore: String, command: String) {
        Data.panels[panelID] = PanelData(lore, command)
    }

    fun acquisitionPanels() = Data.panels

    fun acquisitionReactionPanelData(reactionID: String): PanelData? = Data.panels[reactionID]

    fun acquisitionIconAPI() = Data.ICON_API_URL
}
