package com.maxcmiller.war.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.maxcmiller.war.Main;
import com.maxcmiller.war.Team;

public class MatchManager {

	private Main main;
	public MatchManager(Main instance) {
		this.main = instance;
	}
	
	/*
	 * Creates the two base locations; one for each team
	 */
	Location blueBase = new Location(
			this.main.getServer().getWorld(this.main.getConfig().getString("world")), 
			this.main.getConfig().getDouble("bases.blue.x"), 
			this.main.getConfig().getDouble("bases.blue.y"), 
			this.main.getConfig().getDouble("bases.blue.z"));
	Location redBase = new Location(
			this.main.getServer().getWorld(this.main.getConfig().getString("world")), 
			this.main.getConfig().getDouble("bases.red.x"), 
			this.main.getConfig().getDouble("bases.red.y"), 
			this.main.getConfig().getDouble("bases.red.z"));
	
	/*
	 * Creates two teams
	 */
	public Team blue = new Team("blue");
	public Team red = new Team("red");
	
	/**
	 * Places players in teams, teleports them to their base, starts the match
	 */
	public void startMatch() {
		placePlayersInTeams();
	}
	
	/**
	 * Halves players then places them in a team
	 */
	public void placePlayersInTeams() {
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i < Bukkit.getOnlinePlayers().length / 2) {
				red.addMember(player.getName());
				main.chatManager.msgPlayer(player, "You have been added to the British team.");
			} else {
				blue.addMember(player.getName());
				main.chatManager.msgPlayer(player, "You have been added to the German team.");
			}
			i++;
		}
	}
	
	/**
	 * Telports teams to their base as defined in config
	 */
	public void teleportTeamsToBase() {
		for (String target : blue.getMembers()) {
			Player player = Bukkit.getPlayerExact(target);
			player.teleport(blueBase);
		}
		for (String target : red.getMembers()) {
			Player player = Bukkit.getPlayerExact(target);
			player.teleport(redBase);
		}
	}
	
	/**
	 * Gets the team the player is in
	 */
	public Team getPlayerTeam(String target) {
		if (blue.getMembers().contains(target)) {
			return blue;
		} else {
			return red;
		}
	}
}
