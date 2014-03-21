package com.maxcmiller.war;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.maxcmiller.war.util.ConfigManager;

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
	}
}
