package com.maxcmiller.war.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.maxcmiller.war.Main;
import com.maxcmiller.war.Team;

public class MatchManager {

	public Main main;
	public MatchManager(Main instance) {
		this.main = instance;
	}
	
	/*
	 * Creates an list of all the teams for future access
	 */
	private ArrayList<Team> teams = new ArrayList<Team>();
	
	/*
	 * Creates two teams
	 */
	private Team blue = new Team("blue", this);
	private Team red = new Team("red", this);
	
	/**
	 * Gets the ArrayList of all current teams
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
