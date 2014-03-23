package com.maxcmiller.war.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import com.maxcmiller.war.Team;
import com.maxcmiller.war.enums.Rank;

public class RankManager {

	private static RankManager instance = new RankManager();
	
	private MatchManager manager = MatchManager.getInstance();
	private ChatManager chatManager = ChatManager.getInstance();
	private ConfigManager config = ConfigManager.getInstance();
	
	/**
	 * Gets the instance of this class
	 */
	public static RankManager getInstance() {
		return instance;
	}
	
	public RankManager() {
		/*
		 * Places ranks and their respective chat colors in an map for future use
		 */
		rankColors.put(Rank.GENERAL, ChatColor.DARK_BLUE);
		rankColors.put(Rank.SERGEANT, ChatColor.GREEN);
		rankColors.put(Rank.CORPORAL, ChatColor.YELLOW);
		rankColors.put(Rank.PRIVATE, ChatColor.DARK_AQUA);
	}
	
	/*
	 * HashMap storing the chat color of each rank
	 */
	private HashMap<Rank, ChatColor> rankColors = new HashMap<Rank, ChatColor>();
	
	/**
	 * Sets the rank of a player
	 */
	public void setRank(String target, Rank rank) {
		
		// If the player's rank isn't changing
		if (this.getRank(target).getValue() == rank.getValue()) {
			return;
		}
		
		// If the player is being promoted
		if (this.getRank(target).getValue() < rank.getValue()) {
			chatManager.msgPlayer(Bukkit.getPlayerExact(target), "You have been promoted to " + rank.toString().toLowerCase() + "!");
		}
		
		// If the player is being demoted
		if (this.getRank(target).getValue() > rank.getValue()) {
			chatManager.msgPlayer(Bukkit.getPlayerExact(target), "You have been demoted to " + rank.toString().toLowerCase() + "!");
		}
		
		Team playerTeam = MatchManager.getInstance().getPlayerTeam(target);
		
		List<String> playersInInitialRank = config.getConfig().getStringList(playerTeam.getName() + ".ranks." + getRank(target).toString().toLowerCase());
		playersInInitialRank.remove(target);
		config.getConfig().set(playerTeam.getName() + ".ranks." + getRank(target).toString().toLowerCase(), playersInInitialRank);
		config.saveConfig();
		
		List<String> playersInTargetRank = config.getConfig().getStringList(playerTeam.getName() + ".ranks." + rank.toString().toLowerCase());
		playersInTargetRank.add(target);
		config.getConfig().set(playerTeam.getName() + ".ranks." + rank.toString().toLowerCase(), playersInTargetRank);
		config.saveConfig();
	}
	
	/**
	 * Gets the rank of a player
	 */
	public Rank getRank(String target) {
		
		Team playerTeam = manager.getPlayerTeam(target);
		
		for (Rank rank : Rank.values()) {
			if (config.getConfig().getStringList(playerTeam.getName() + ".ranks." + rank.toString().toLowerCase()).contains(target)) {
				return rank;
			}
		}
		return null;
	}
	
	/**
	 * Gets the ChatColor of a specified rank
	 */
	public ChatColor getColor(Rank rank) {
		return rankColors.get(rank);
	}
	
	/**
	 * Returns a list of players in a specified rank
	 */
	public ArrayList<String> getPlayersInRank(Rank rank) {
		List<String> playersInRank = ConfigManager.getInstance().getConfig().getStringList((rank.toString().toLowerCase()));
		return (ArrayList<String>) playersInRank;
	}
}