package com.maxcmiller.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import com.maxcmiller.war.managers.GuiManager;

public class GuiClick implements Listener {
	
	GuiManager guiManager = GuiManager.getInstance();
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		for (Inventory i : guiManager.getGUI().getAllInventories()) {
			
			/*
			 * Checks if the clicked inventory is one of the rank-specific GUIs 
			 */
			if (e.getInventory().getName().equals(i.getName())) {
				
				// Avoids a NullPointerException
				if (e.getCurrentItem() != null) {
					
					/*
					 * Manage commands for each item (making sure to compare itemstacks using .getType())
					 */
				}
			}
		}
	}
}
