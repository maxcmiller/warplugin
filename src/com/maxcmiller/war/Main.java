package com.maxcmiller.war;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxcmiller.war.listeners.InventoryClick;
import com.maxcmiller.war.listeners.PlayerInteract;
import com.maxcmiller.war.managers.GuiManager;
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
		
		MatchManager.getInstance().setup();
		
		getCommand("e").setExecutor(GuiManager.getInstance());
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
	}
}
