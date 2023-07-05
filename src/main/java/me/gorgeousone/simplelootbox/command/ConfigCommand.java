package me.gorgeousone.simplelootbox.command;

import me.gorgeousone.simplelootbox.cmdframework.command.BaseCommand;
import org.bukkit.command.CommandSender;

public class ConfigCommand extends BaseCommand {
	
	
	public ConfigCommand() {
		super("lootbox");
		addAlias("lb");
		addAlias("box");
	}
	
	@Override
	protected void onCommand(CommandSender sender, String[] args) {
		sender.sendMessage("bleh");
	}
}
