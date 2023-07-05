package me.gorgeousone.simplelootchests.command;

import me.gorgeousone.simplelootchests.cmdframework.command.BaseCommand;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class ConfigCommand extends BaseCommand {
	
	private final JavaPlugin plugin;
	
	public ConfigCommand(JavaPlugin plugin) {
		super("lootchests");
		this.plugin = plugin;
		addAlias("lc");
		addAlias("loot");
		
		setPlayerRequired(true);
	}
	
	@Override
	protected void onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		sender.sendMessage("nuh");
		ItemStack item = new ItemStack(Material.COOKIE);
		
		AnvilGUI.Builder builder = new AnvilGUI.Builder()
				.plugin(plugin)
				.title("Do you like cookies?")
				.itemLeft(item);
		
		builder.onClick((slot, stateSnapshot) -> Collections.emptyList());
		builder.open(player);
	}
}
