package com.maxcmiller.war;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.maxcmiller.war.enums.Rank;
import com.maxcmiller.war.managers.ConfigManager;
import com.maxcmiller.war.managers.RankManager;

public class Team {

	private ConfigManager config = ConfigManager.getInstance();
	
	private String teamName;
	private ChatColor color;
	private Location base;
	
	/**
	 * Creates a team from a name
	 */
	public Team(String name) {
		this.teamName = name;
		
		/*
		 * Loads the Location of the team's base/HQ
		 */
		base = new Location(
				Bukkit.getServer().getWorld(config.getConfig().getString("world")),
				config.getConfig().getDouble(this.getName() + ".x"), 
				config.getConfig().getDouble(this.getName() + ".y"), 
				config.getConfig().getDouble(this.getName() + ".z"));
		
		/*
		 * Creates the chat color of the team
		 */
		if (name.equals("red")) {
			this.color = ChatColor.RED;
		} else {
			this.color = ChatColor.BLUE;
		}
	}
	
	/**
	 * Gets the name of the team
	 */
	public String getName() {
		return this.teamName;
	}
	
	/**
	 * Gets the chat color of the team
	 */
	public ChatColor getColor() {
		return this.color;
	}
	
	/**
	 * Sets the base/HQ of the team
	 */
	public void setBase(Location base) {
		this.base = base;
	}
	
	/**
	 * Gets the base/HQ of the team
	 */
	public Location getBase() {
		return this.base;
	}
	
	/**
	 * Adds a member to the team
	 */
	public void addMember(String player, Rank rank) {
		List<String> playersInRank = config.getConfig().getStringList(this.getName() + ".ranks." + rank.toString().toLowerCase());
		playersInRank.add(player);
		config.saveConfig();
	}
	
	/**
	 * Removes a member from the team
	 */
	public void removeMember(String player) {
		
		Rank playerRank = RankManager.getInstance().getRank(player);
		
		List<String> members = config.getConfig().getStringList(this.getName() + ".ranks." + playerRank.toString().toLowerCase());
		members.remove(player);
	}
	
	/**
	 * Returns a list of all the team's members
	 */
	public ArrayList<String> getMembers() {
		
		ArrayList<String> members = new ArrayList<String>();
		
		for (Rank rank : Rank.values()) {
			for (String player : config.getConfig().getStringList(this.getName() + ".ranks." + rank.toString().toLowerCase())) {
				members.add(player);
			}
		}
		return members;
	}
}