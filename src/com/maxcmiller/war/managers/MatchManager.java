package com.maxcmiller.war.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.maxcmiller.war.Team;
import com.maxcmiller.war.enums.Rank;

public class MatchManager {

	private static MatchManager instance = new MatchManager();
	
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
	
	/*
	 * Creates two new teams (red and blue)
	 */
	private Team blue = new Team("blue");
	private Team red = new Team("red");

	/**
	 * Gets the ArrayList of the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}
	
	/**
	 * Sets up the match
	 */
	public void setup() {
		teams.add(blue);
		teams.add(red);
	}
	
	/**
	 * Places players in teams, teleports them to their base, starts the match
	 */
	public void startMatch() {
		placePlayersInTeams();
		teleportTeamsToBase();
		setupPlayersInventories();
	}
	
	/**
	 * Halves players then places them in a team
	 */
	private void placePlayersInTeams() {
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			Rank playerRank = RankManager.getInstance().getRank(player.getName());
			
			if (i < Bukkit.getOnlinePlayers().length / 2) {
				red.addMember(player.getName(), playerRank);
				ChatManager.getInstance().msgPlayer(player, "You have been added to the Red team.");
			} else {
				blue.addMember(player.getName(), playerRank);
				ChatManager.getInstance().msgPlayer(player, "You have been added to the Blue team.");
			}
			i++;
		}
	}
	
	/**
	 * Telports teams to their base as defined in config
	 */
	private void teleportTeamsToBase() {
		for (Team team : this.getTeams()) {
			for (String target : team.getMembers()) {
				Player player = Bukkit.getPlayerExact(target);
				player.teleport(team.getBase());
			}
		}
	}
	
	/**
	 * Sets up the inventory of each player with necessary items
	 */
	private void setupPlayersInventories() {
		for (Team team : this.getTeams()) {
			for (String member : team.getMembers()) {
				Player player = Bukkit.getPlayerExact(member);
				player.getInventory().addItem(GuiManager.getInstance().getGUI().commandBook);
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
	
	/**
	 * Gets a team object by its name
	 */
	public Team getTeamByName(String teamName) {
		for (Team team : this.getTeams()) {
			if (team.getName().equals(teamName)) {
				return team;
			}
		}
		return null;
	}
}
