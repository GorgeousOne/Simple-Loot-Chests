package me.gorgeousone.simplelootbox.command;

import me.gorgeousone.simplelootbox.cmdframework.command.BaseCommand;
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
		super("lootbox");
		this.plugin = plugin;
		addAlias("lb");
		addAlias("box");
		
		setPlayerRequired(true);
	}
	
	@Override
	protected void onCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		ItemStack item = new ItemStack(Material.COOKIE);
		
		AnvilGUI.Builder builder = new AnvilGUI.Builder()
				.plugin(plugin)
				.title("Do you like cookies?")
				.itemLeft(item);
		
		builder.onClick((slot, stateSnapshot) -> Collections.emptyList());
		builder.open(player);
	}
}
