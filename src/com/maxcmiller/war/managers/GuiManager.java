package com.maxcmiller.war.managers;

import com.maxcmiller.war.enums.Rank;

public class GuiManager {
	
	private static GuiManager instance = new GuiManager();
	
	/**
	 * Gets the instance of this class
	 */
	public static GuiManager getInstance() {
		return instance;
	}
	
	public void createGui(Rank rank) {
		if (rank.equals(Rank.CORPORAL)) {
			
		}
	}
}
