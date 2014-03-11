package com.maxcmiller.war;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class Team {
	
	private ArrayList<String> members = new ArrayList<String>();
	private String name;
	private ChatColor color;
	
	/*
	 * Default constructor
	 */
	public Team(String name) {
		this.name = name;
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