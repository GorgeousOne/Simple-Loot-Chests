package me.gorgeousone.simplelootchests.command;

import me.gorgeousone.simplelootchests.menu.LootTableListGUI;
import me.gorgeousone.simplelootchests.cmdframework.command.BaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * What are you listing?
 */
public class ListChestsCommand extends BaseCommand {
	
	private final JavaPlugin plugin;
	private final LootTableListGUI lootTableList;
	
	public ListChestsCommand(JavaPlugin plugin, LootTableListGUI lootTableList) {
		super("list");
		this.plugin = plugin;
		this.lootTableList = lootTableList;
		setPlayerRequired(true);
	}
	
	@Override
	protected void onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		lootTableList.open(player);
	}
}
