package com.maxcmiller.war;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxcmiller.war.listeners.GuiClick;
import com.maxcmiller.war.listeners.CommandBookOpen;
import com.maxcmiller.war.listeners.WeaponFire;
import com.maxcmiller.war.managers.GuiManager;
import com.maxcmiller.war.managers.ConfigManager;
import com.maxcmiller.war.managers.MatchManager;

public class Main extends JavaPlugin {
	
	@Override
	public void onDisable() {
		ConfigManager.getInstance().disable(this);
	}
	
	@Override
	public void onEnable() {
		ConfigManager.getInstance().setup(this);
		
		MatchManager.getInstance().setup();
		
		getCommand("e").setExecutor(GuiManager.getInstance());
		
		Bukkit.getServer().getPluginManager().registerEvents(new CommandBookOpen(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GuiClick(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WeaponFire(), this);
	}
}
