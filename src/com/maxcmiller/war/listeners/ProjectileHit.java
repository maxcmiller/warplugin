package com.maxcmiller.war.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.maxcmiller.war.enums.Weapon;

public class ProjectileHit implements Listener {
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (!(e.getEntity() instanceof Arrow)) return;
		
		for (Weapon w : Weapon.values()) {
			w.run(e);
		}
	}
}
