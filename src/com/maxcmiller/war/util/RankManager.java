package com.maxcmiller.war.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import com.maxcmiller.war.enums.Rank;

public class RankManager {

	private static RankManager instance = new RankManager();
	
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
	
	/*
	 * HashMap storing player usernames (as the key) and their rank as an enum (as the value)
	 */
	private HashMap<String, Rank> playerRanks = new HashMap<String, Rank>();
	
	/**
	 * Sets the rank of a player
	 */
	public void setRank(String target, Rank rank) {
		playerRanks.put(target, rank);
	}
	
	/**
	 * Gets the rank of a player
	 */
	public Rank getRank(String target) {
		return playerRanks.get(target);
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
		ArrayList<String> playersInRank = new ArrayList<String>();				// Creates a new list of people in the rank
		Iterator<Entry<String, Rank>> it = playerRanks.entrySet().iterator();	// Creates an iterator to loop through the map
	    while (it.hasNext()) {
	        Entry<String, Rank> pairs = (Entry<String, Rank>) it.next();		// A new key/value pair from the iterator
	        if (pairs.getValue().equals(rank)) {								// If the current pair's rank is the paramater
	        	playersInRank.add(pairs.getKey().toString());					// Adds the pair's value (rank) to the list
	        }
	        it.remove();														// For safety
	    }
	    return playersInRank;
	}
}