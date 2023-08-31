package me.gorgeousone.simplelootchests.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * What kind of inventories does this listen to?
 */
public class InventoryListener implements Listener {

	private final JavaPlugin plugin;
	private final GUIManager guiManager;
	
	public InventoryListener(JavaPlugin plugin, GUIManager guiManager) {
		this.plugin = plugin;
		this.guiManager = guiManager;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryGUI gui = guiManager.getGUI(event.getInventory());
		
		if (gui != null) {
			event.setCancelled(gui.handleClick(event));
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		InventoryGUI gui = guiManager.getGUI(event.getInventory());
		
		if (gui != null) {
			gui.onClose((Player) event.getPlayer());
		}
	}
}
