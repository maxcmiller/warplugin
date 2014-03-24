package com.maxcmiller.war;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.maxcmiller.war.enums.Rank;
import com.maxcmiller.war.managers.RankManager;

public class GUI {
	
	RankManager rankManager = RankManager.getInstance();
	
	public Inventory promote;
	private Inventory privateInv, corporalInv, sergeantInv, generalInv;
	public ItemStack commandBook, medic, location, bombardment, promotePlayer;
	
	public GUI() {
		/*
		 * Create all the inventories for each rank
		 */
		promote = Bukkit.createInventory(null, 36, "Promote a player");
		privateInv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Commands for privates");
		corporalInv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Commands for corporals");
		sergeantInv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Commands for sergeants");
		generalInv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Commands for generals");
		
		/*
		 * Create all the items for the GUI commands
		 */
		commandBook = createNormalItem(Material.BOOK, ChatColor.GREEN + "Right click for commands");
		medic = createPotion(new Potion(PotionType.INSTANT_HEAL), ChatColor.RED, "Request a medic");
		location = createNormalItem(Material.COMPASS, ChatColor.YELLOW + "Check your current location");
		bombardment = createNormalItem(Material.TNT, ChatColor.DARK_RED + "Bombard the enemy's current HQ");
		promotePlayer = createNormalItem(Material.ANVIL, ChatColor.GREEN + "Promote a player");
		
		/*
		 * Adds the items to the GUI inventory screen according to the rank
		 */
		privateInv.addItem(medic);
		privateInv.addItem(location);
		
		corporalInv.addItem(medic);
		corporalInv.addItem(location);
		
		sergeantInv.addItem(medic);
		sergeantInv.addItem(location);
		sergeantInv.addItem(promotePlayer);
		
		generalInv.addItem(medic);
		generalInv.addItem(location);
		generalInv.addItem(bombardment);
		generalInv.addItem(promotePlayer);
	}

	private ItemStack createNormalItem(Material normalItem, String name) {
		ItemStack is = new ItemStack(normalItem);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}
	
	private ItemStack createPotion(Potion potion, ChatColor color, String name) {
		ItemStack is = potion.toItemStack(1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(color + name);
		is.setItemMeta(im);
		return is;
	}
	
	/**
	 * Returns the command inventory/GUI for the specified rank
	 */
	public Inventory getInventory(Rank rank) {
		if (rank == Rank.PRIVATE) {
			return privateInv;
		} else if (rank == Rank.CORPORAL) {
			return corporalInv;
		} else if (rank == Rank.PRIVATE) {
			return sergeantInv;
		} else if (rank == Rank.GENERAL) {
			return generalInv;
		}
		return null;
	}
	
	/**
	 * Returns an arraylist with all the ranks' inventories
	 */
	public ArrayList<Inventory> getAllInventories() {
		ArrayList<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(generalInv);
		inventories.add(sergeantInv);
		inventories.add(corporalInv);
		inventories.add(privateInv);
		return inventories;
	}
	
	/**
	 * Returns an inventory/GUI for the requested player
	 * @return Be careful: can return null!
	 */
	public Inventory getPromoteInventory(Player player) {
		
		// Clears the promote inventory for re-use
		promote.clear();
		
		// Makes sure the player that is requesting the promote inventory can actually promote someone, ie. is high enough rank
		Rank senderRank = rankManager.getRank(player.getName());
		if (!(senderRank == Rank.GENERAL || senderRank == Rank.SERGEANT)) {
			return null;
		}
		
		// Stores the sender rank value as an integer
		int senderRankValue = senderRank.getValue();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			// If the targeted player is low enough to be promoted by the sender player
			if (rankManager.getRank(p.getName()).getValue() <= senderRankValue - 2) {
				
				// Create a new itemstack with their head as displayName and their rank as lore
				ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta sm = (SkullMeta) is.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add("Rank: " + rankManager.getColor(rankManager.getRank(p.getName())) + rankManager.getRank(p.getName()).toString().toLowerCase());
				sm.setLore(lore);
				sm.setDisplayName(p.getName());
				is.setItemMeta(sm);
				promote.addItem(is);
			}
		}
		return promote;
	}
}
