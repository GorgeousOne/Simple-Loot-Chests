package me.gorgeousone.simplelootchests.gui;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;


public class GUIManager {
	
	private final Map<Inventory, InventoryGUI> guis;
	
	public GUIManager() {
		guis = new HashMap<>();
	}
	
	public void registerGUI(InventoryGUI gui) {
		guis.put(gui.getInventory(), gui);
	}
	
	public void removeGUI(Inventory gui) {
		guis.remove(gui);
	}
	
	public InventoryGUI getGUI(Inventory gui) {
		return guis.getOrDefault(gui, null);
	}
}
