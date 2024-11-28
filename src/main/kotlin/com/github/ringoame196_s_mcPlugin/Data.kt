package com.github.ringoame196_s_mcPlugin

import net.dv8tion.jda.api.JDA

object Data {
	var JDA: JDA? = null
	var displayChannelID: String? = null
	val reaction = mutableMapOf<String, String>()
}