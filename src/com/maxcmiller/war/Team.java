package com.maxcmiller.war;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.maxcmiller.war.util.MatchManager;

public class Team {
	
	private ArrayList<String> members = new ArrayList<String>();
	private String name;
	private ChatColor color;
	private Location base;
	
	/**
	 * Creates a team with a name and an instance of the TeamManager object
	 */
	public Team(String name, MatchManager manager) {
		this.name = name;
		manager.getTeams().add(this);					// Adds the team to the list of teams in the manager
		base = new Location(
				manager.main.getServer().getWorld(manager.main.getConfig().getString("world")), 
				manager.main.getConfig().getDouble("bases." + this.getName() + ".x"), 
				manager.main.getConfig().getDouble("bases." + this.getName() + ".y"), 
				manager.main.getConfig().getDouble("bases." + this.getName() + ".z"));
	}
	
	/**
	 * Gets the name of the team
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the chat color of the team
	 */
	public ChatColor getColor() {
		return this.color;
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