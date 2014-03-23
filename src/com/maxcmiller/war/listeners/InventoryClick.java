package com.maxcmiller.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.maxcmiller.war.managers.GuiManager;
import com.maxcmiller.war.managers.RankManager;

public class InventoryClick implements Listener {
	
	GuiManager guiManager = GuiManager.getInstance();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		for (Inventory i : guiManager.getGUI().getAllInventories()) {
			if (e.getInventory().equals(i)) {
				
				/*
				 * Do commands for each item
				 */
			}
		}
		if (e.getInventory().equals(guiManager.getGUI().promote)) {
			if (RankManager.getInstance().getRank(e.getWhoClicked().getName()) != null) { // change
				
			}
		}
	}
}
