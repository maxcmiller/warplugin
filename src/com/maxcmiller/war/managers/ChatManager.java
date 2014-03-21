package com.maxcmiller.war.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.maxcmiller.war.Team;
import com.maxcmiller.war.enums.Rank;

public class ChatManager {
	
	private static ChatManager instance = new ChatManager();
	
	/**
	 * Gets the instance of this class
	 */
	public static ChatManager getInstance() {
		return instance;
	}
	
	/**
	 * Sends a message to the target player
	 */
	public void msgPlayer(Player target, String message) {
		target.sendMessage(ChatColor.GOLD + "[War] " + ChatColor.GRAY + message);
	}
	
	/**
	 * Broadcasts a message to a team
	 */
	public void msgTeam(Team team, String message) {
		for (String username : team.getMembers()) {
			Player player = Bukkit.getPlayer(username);
			player.sendMessage(team.getColor() + "[" + team.getName().toUpperCase() + "] " + ChatColor.GRAY + message);
		}
	}
	
	/**
	 * Broadcasts a message to a rank, in all teams
	 */
	public void msgGlobalRank(Rank rank, String message) {
		for (String username : RankManager.getInstance().getPlayersInRank(rank)) {
			Player player = Bukkit.getPlayer(username);
			player.sendMessage(RankManager.getInstance().getColor(rank) + "[" + rank.name().toString() + "] " + ChatColor.GRAY + message);
		}
	}
	
	/**
	 * Broadcasts a message to a rank in a specific team
	 */
	public void msgRank(Team team, Rank rank, String message) {
		for (String username : RankManager.getInstance().getPlayersInRank(rank)) {
			if (MatchManager.getInstance().getPlayerTeam(username).equals(team)) {		// Checks if the player is in the specified team
				Player player = Bukkit.getPlayer(username);
				player.sendMessage(RankManager.getInstance().getColor(rank) + "[" + rank.name().toString() + "] " + ChatColor.GRAY + message);
			}
		}
	}
}
