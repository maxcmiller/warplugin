package com.maxcmiller.war.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.maxcmiller.war.managers.GuiManager;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
			if (e.getItem().getType() == Material.BOOK) {
				if (e.getItem().getItemMeta().getDisplayName().equals(GuiManager.getInstance().getGUI().commandBook.getItemMeta().getDisplayName())) {
					GuiManager.getInstance().showGUI(e.getPlayer());
					e.setCancelled(true);
				}
			}
		}
	}
}
