package com.maxcmiller.war.managers;

import java.util.ArrayList;

import org.bukkit.entity.Entity;

public class WeaponManager {
	
	private static WeaponManager instance = new WeaponManager();
	
	/**
	 * Gets the instance of this class
	 */
	public static WeaponManager getInstance() {
		return instance;
	}
	
	/*
	 * Stores the projectiles currently in the air
	 */
	public ArrayList<Entity> projectiles = new ArrayList<Entity>();
}
