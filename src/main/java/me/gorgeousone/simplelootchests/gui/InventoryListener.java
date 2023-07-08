package me.gorgeousone.simplelootchests.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryListener implements Listener {

	private final JavaPlugin plugin;
	private final GUIManager guiManager;
	
	public InventoryListener(JavaPlugin plugin, GUIManager guiManager) {
		this.plugin = plugin;
		this.guiManager = guiManager;
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		InventoryAction action = event.getAction();
		if (action != InventoryAction.PICKUP_ALL && action != InventoryAction.PICKUP_HALF) {
			return;
		}
		InventoryGUI gui = guiManager.getGUI(event.getInventory());
		
		if (gui == null) {
			return;
		}
		event.setCancelled(true);
		gui.handleClick(event);
	}
}
