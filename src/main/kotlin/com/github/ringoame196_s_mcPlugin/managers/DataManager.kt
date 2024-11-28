package com.github.ringoame196_s_mcPlugin.managers

import com.github.ringoame196_s_mcPlugin.Data

object DataManager {
	fun addReaction(reactionID: String, command: String) {
		Data.reaction[reactionID] = command
	}

	fun acquisitionReactionCommand(reactionID: String): String? = Data.reaction[reactionID]
}