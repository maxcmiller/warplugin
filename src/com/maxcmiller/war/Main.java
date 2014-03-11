package com.maxcmiller.war;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxcmiller.war.util.ChatManager;
import com.maxcmiller.war.util.MatchManager;
import com.maxcmiller.war.util.RankManager;

public class Main extends JavaPlugin {
	
	/*
	 * Creates logger object to print to console
	 */
	public final Logger logger = Logger.getLogger("Minecraft");
	
	/*
	 * Creates instances of all the required managers
	 */
	public MatchManager manager = new MatchManager(this);
	public ChatManager chatManager = new ChatManager(this);
	public RankManager rankManager = new RankManager(this);
	
	@Override
	public void onDisable() {
		this.saveConfig();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " " + pdfFile.getVersion() + " has been disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() +  pdfFile.getVersion() + " has been enabled");
	}
}
