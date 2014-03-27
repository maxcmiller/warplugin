package com.maxcmiller.war.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import com.maxcmiller.war.enums.Weapon;

public class WeaponFire implements Listener {
	
	@EventHandler
	public void onWeaponFire(PlayerInteractEvent e) {
		if (!e.hasItem()) return;
		if (!(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
		if (!(e.getItem().getType() == Material.BOW)) return;
		
		ItemMeta im = e.getItem().getItemMeta();
		
		for (Weapon w : Weapon.values()) {
			if (im.getDisplayName() == w.getName()) {
				e.setCancelled(true);
				w.run(e);
			}
		}
	}
}
