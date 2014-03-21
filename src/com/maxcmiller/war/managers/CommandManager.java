package com.maxcmiller.war.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {
	
	private static CommandManager instance = new CommandManager();
	private RankManager rankManager = RankManager.getInstance();
	private ChatManager chatManager = ChatManager.getInstance();
	
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
				GuiManager.getInstance().createGui(rankManager.getRank(sender.getName()));
			}
		}
		return true;
	}
}
