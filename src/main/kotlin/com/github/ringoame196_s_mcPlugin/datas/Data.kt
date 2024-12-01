package com.github.ringoame196_s_mcPlugin.datas

import net.dv8tion.jda.api.JDA

object Data {
	var JDA: JDA? = null
	var displayChannelID: String? = null
	var permitRollID: String? = null
	val panels = mutableMapOf<String, PanelData>()
	const val ICON_API_URL = "https://api.mineatar.io/face/@uuid?scale=32"
}
