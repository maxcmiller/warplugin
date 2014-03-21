package com.maxcmiller.war.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.maxcmiller.war.Team;

public class MatchManager {

	private static MatchManager instance = new MatchManager();
	
	private Team blue = new Team("blue");
	private Team red = new Team("red");
	
	/**
	 * Gets the instance of this class
	 */
	public static MatchManager getInstance() {
		return instance;
	}
	
	/*
	 * Creates an list of all the teams for future access
	 */
	private ArrayList<Team> teams = new ArrayList<Team>();
	
	/**
	 * Loads the two teams
	 */
	public void loadTeams() {
		teams.add(blue);
		teams.add(red);
	}
	
	/**
	 * Gets the ArrayList of the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}
	
	/**
	 * Places players in teams, teleports them to their base, starts the match
	 */
	public void startMatch() {
		placePlayersInTeams();
		teleportTeamsToBase();
	}
	
	/**
	 * Halves players then places them in a team
	 */
	public void placePlayersInTeams() {
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i < Bukkit.getOnlinePlayers().length / 2) {
				red.addMember(player.getName());
				ChatManager.getInstance().msgPlayer(player, "You have been added to the British team.");
			} else {
				blue.addMember(player.getName());
				ChatManager.getInstance().msgPlayer(player, "You have been added to the German team.");
			}
			i++;
		}
	}
	
	/**
	 * Telports teams to their base as defined in config
	 */
	public void teleportTeamsToBase() {
		for (Team team : this.getTeams()) {
			for (String target : team.getMembers()) {
				Player player = Bukkit.getPlayerExact(target);
				player.teleport(team.getBase());
			}
		}
	}
	
	/**
	 * Gets the team the player is in
	 */
	public Team getPlayerTeam(String target) {
		for (Team team : this.getTeams()) {
			if (team.getMembers().contains(target)) {
				return team;
			}
		}
		return null;
	}
}
