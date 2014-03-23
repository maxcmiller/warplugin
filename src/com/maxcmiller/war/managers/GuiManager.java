package com.maxcmiller.war.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.maxcmiller.war.GUI;

public class GuiManager implements CommandExecutor {
	
	private static GuiManager instance = new GuiManager();
	
	private RankManager rankManager = RankManager.getInstance();
	
	/*
	 * Creates a the GUI class that holds the ranks' inventory data
	 */
	private GUI gui = new GUI();
	
	/**
	 * Gets the instance of this class
	 */
	public static GuiManager getInstance() {
		return instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		/*
		 * Player commands
		 */
		if (sender instanceof Player) {
			/*
			 * Command to open the GUI
			 */
			if (commandLabel.equalsIgnoreCase("e")) {
				Player player = (Player) sender;
				player.getInventory().addItem(gui.commandBook);
				return true;
			}
		}
		return true;
	}
	
	/**
	 * Returns the GUI object containing itemstacks and inventories
	 */
	public GUI getGUI() {
		return gui;
	}
	
	/**
	 * Shows an specific inventory to a player according to their rank
	 */
	public void showGUI(Player p) {
		p.openInventory(gui.getInventory(rankManager.getRank(p.getName())));
	}
}
