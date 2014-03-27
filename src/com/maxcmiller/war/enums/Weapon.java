package com.maxcmiller.war.enums;

import java.util.ArrayList;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public enum Weapon {
	
	RIFLE("Rifle", new WeaponRunnable() {
		public void run(PlayerInteractEvent e) {
			Player player = e.getPlayer();
			Projectile p = player.launchProjectile(Arrow.class);
			p.setVelocity(p.getVelocity().multiply(3));
			projectiles.add(p);
		}

		public void run(ProjectileHitEvent e) {
			
		}
	}), BAZOOKA("Bazooka", new WeaponRunnable() {
		public void run(PlayerInteractEvent e) {
			
		}

		public void run(ProjectileHitEvent e) {
			
		}
	});
	
	private final String name;
	private static ArrayList<Entity> projectiles = new ArrayList<Entity>();
	private WeaponRunnable run;
	
	private Weapon(String name, WeaponRunnable run) {
		this.name = name;
		this.run = run;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Entity> getProjectiles() {
		return Weapon.projectiles;
	}
	
	public void run(PlayerInteractEvent e) {
		run.run(e);
	}
	
	public void run(ProjectileHitEvent e) {
		run.run(e);
	}
	
	public Weapon getWeaponByName(String s) {
		for (Weapon w : Weapon.values()) {
			if (w.getName() == s) {
				return w;
			}
		}
		return null;
	}
}

abstract class WeaponRunnable { 
	public abstract void run(PlayerInteractEvent e);
	public abstract void run(ProjectileHitEvent e);
}
