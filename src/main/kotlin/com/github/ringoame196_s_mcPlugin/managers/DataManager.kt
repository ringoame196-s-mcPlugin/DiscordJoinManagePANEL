package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data
import com.github.ringoame196_s_mcPlugin.PanelData

object DataManager {
    fun addReaction(panelID: String, lore: String, command: String) {
        Data.panels[panelID] = PanelData(lore, command)
    }

    fun acquisitionReactions() = Data.panels

    fun acquisitionReactionPanelData(reactionID: String): PanelData? = Data.panels[reactionID]

    fun acquisitionIconAPI() = Data.ICON_API_URL
}
