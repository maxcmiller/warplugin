package com.maxcmiller.war;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.maxcmiller.war.util.ConfigManager;
import com.maxcmiller.war.util.MatchManager;

public class Team {

	public MatchManager manager = MatchManager.getInstance();
	
	private ArrayList<String> members = new ArrayList<String>();
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
				Bukkit.getServer().getWorld(ConfigManager.getInstance().getConfig().getString("world")),
				ConfigManager.getInstance().getConfig().getDouble(this.getName() + ".x"), 
				ConfigManager.getInstance().getConfig().getDouble(this.getName() + ".y"), 
				ConfigManager.getInstance().getConfig().getDouble(this.getName() + ".z"));
		
		/*
		 * Loads the members of the team from the config
		 */
		this.members = (ArrayList<String>) ConfigManager.getInstance().getConfig().getStringList(this.getName() + ".members");
		
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
	public void addMember(String player) {
		members.add(player);
	}
	
	/**
	 * Removes a member from the team
	 */
	public void removeMember(String player) {
		members.remove(player);
	}
	
	/**
	 * Returns a list of all the team's members
	 */
	public ArrayList<String> getMembers() {
		return this.members;
	}
}