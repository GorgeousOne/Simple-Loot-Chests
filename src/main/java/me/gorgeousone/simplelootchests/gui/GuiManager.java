package me.gorgeousone.simplelootchests.gui;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;


public class GuiManager {
	
	private final Map<Inventory, InventoryGui> guis;
	
	public GuiManager() {
		guis = new HashMap<>();
	}
	
	public void registerGui(InventoryGui gui) {
		guis.put(gui.getInventory(), gui);
	}
	
	public void removeGui(Inventory gui) {
		guis.remove(gui);
	}
	
	public InventoryGui getGui(Inventory gui) {
		return guis.getOrDefault(gui, null);
	}
}
