package com.maxcmiller.war;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.maxcmiller.war.managers.CommandManager;
import com.maxcmiller.war.managers.ConfigManager;
import com.maxcmiller.war.managers.MatchManager;

public class Main extends JavaPlugin {
	
	/*
	 * Creates logger object to print to console
	 */
	public final Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onDisable() {
		ConfigManager.getInstance().disable(this);
	}
	
	@Override
	public void onEnable() {
		ConfigManager.getInstance().setup(this);
		getCommand("e").setExecutor(CommandManager.getInstance());
		MatchManager.getInstance().setup();
	}
}
