package com.maxcmiller.war.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager();
	
	PluginDescriptionFile pdfFile;
	FileConfiguration config;
	Plugin plugin;
	
	/**
	 * Gets the instance of this class
	 */
	public static ConfigManager getInstance() {
		return instance;
	}

	/**
	 * Sets up the configuration folder and file
	 */
	public void setup(Plugin p) {
		this.plugin = p;
		
		File file = new File(plugin.getDataFolder() + File.separator + "config.yml");
		if (!file.exists()) {
			plugin.saveDefaultConfig();
			plugin.getLogger().info("Generating config.yml");
		} else {
			plugin.getLogger().info("Using existing config.yml in " + plugin.getDataFolder() + File.separator + "config.yml");
		}
		
		this.pdfFile = plugin.getDescription();
		
		plugin.getLogger().info(pdfFile.getName() +  pdfFile.getVersion() + " has been enabled");
	}
	
	public void disable(Plugin p) {
		this.saveConfig();
		plugin.getLogger().info(pdfFile.getName() +  pdfFile.getVersion() + " has been disabled");
	}
	
	/**
	 * Returns the plugin's configuration file
	 */
	public FileConfiguration getConfig() {
		return config;
	}
	
	/**
	 * Saves the plugin's configuration file to disk
	 */
	public void saveConfig() {
		plugin.saveConfig();
	}
	
	/**
	 * Gets the plugin's description file with version info and more
	 */
	public PluginDescriptionFile getDescription() {
		return pdfFile;
	}
}
