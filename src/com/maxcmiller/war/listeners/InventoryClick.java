package com.maxcmiller.war.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.SkullMeta;

import com.maxcmiller.war.enums.Rank;
import com.maxcmiller.war.managers.GuiManager;
import com.maxcmiller.war.managers.RankManager;

public class InventoryClick implements Listener {
	
	GuiManager guiManager = GuiManager.getInstance();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
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
					
					// Promotion GUI
					if (e.getCurrentItem().getType() == guiManager.getGUI().promotePlayer.getType()) {
						Rank rankOfClicker = RankManager.getInstance().getRank(e.getWhoClicked().getName());
						// Checks if the clicker is high enough in rank to promote another player
						if (rankOfClicker == Rank.SERGEANT || rankOfClicker == Rank.GENERAL) {
							Player player = (Player) e.getWhoClicked();
							player.openInventory(guiManager.getGUI().getPromoteInventory((Player) e.getWhoClicked()));
						}
					}
				}
			}
		}
		/*
		 * If the inventory is the promotion GUI
		 */
		if (e.getInventory().getName().equals(guiManager.getGUI().promote.getName())) {
			
			// If the clicked item is a skull
			if (e.getCurrentItem().equals(Material.SKULL_ITEM)) {
				SkullMeta skullData = (SkullMeta) e.getCurrentItem().getItemMeta();
				String skullName = skullData.getDisplayName();
				Rank clickedRank = RankManager.getInstance().getRank(skullName);
				Rank clickerRank = RankManager.getInstance().getRank(e.getWhoClicked().getName());
				
				// Code to promote a private (by a sergeant)
				if (clickerRank == Rank.SERGEANT) {
					if (clickedRank == Rank.PRIVATE) {
						RankManager.getInstance().promote(Bukkit.getPlayer(skullName));
					}
				}
				
				// Code to promote a private/corporal (by a general)
				if (clickerRank == Rank.GENERAL) {
					if (clickedRank.getValue() <= Rank.CORPORAL.getValue()) {
						RankManager.getInstance().promote(Bukkit.getPlayer(skullName));
					}
				}
			}
		}
	}
}
