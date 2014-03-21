package com.maxcmiller.war.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxcmiller.war.GUI;

public class CommandManager implements CommandExecutor {
	
	private static CommandManager instance = new CommandManager();
	
	private GUI gui = new GUI();
	
	/**
	 * Gets the instance of this class
	 */
	public static CommandManager getInstance() {
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
				show(player);
				player.sendMessage("You typed /e");
				return true;
			}
		}
		return true;
	}
	
	/**
	 * Shows an specific inventory to a player according to their rank
	 */
	public void show(Player p) {
		p.openInventory(gui.getInventory(RankManager.getInstance().getRank(p.getName())));
	}
}
