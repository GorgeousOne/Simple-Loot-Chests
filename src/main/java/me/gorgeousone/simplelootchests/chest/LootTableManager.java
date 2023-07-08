package me.gorgeousone.simplelootchests.chest;

import me.gorgeousone.simplelootchests.menu.LootTableGUI;
import me.gorgeousone.simplelootchests.menu.LootTableListGUI;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;

public class LootTableManager {
	
	private final JavaPlugin plugin;
	private final List<LootTable> lootTables;
	
	private final GUIManager guiManager;
	private final LootTableListGUI lootTableList;
	private final List<LootTableGUI> lootTableGUIs;
	
	
	public LootTableManager(JavaPlugin plugin, GUIManager guiManager) {
		this.plugin = plugin;
		this.guiManager = guiManager;
		this.lootTables = new LinkedList<>();
		this.lootTableList = new LootTableListGUI(plugin, this, guiManager);
		this.lootTableGUIs= new LinkedList<>();
		
		registerGUIs();
	}
	
	private void registerGUIs() {
	
//		for (LootTable lootTable : lootTables) {
//			LootTableGUI gui = new LootTableGUI(lootTable);
//			lootTableGUIs.add(gui);
//			guiManager.registerGUI(gui);
//		}
	}
	
	public void addLootTable(LootTable lootTable) {
		lootTables.add(lootTable);
		LootTableGUI gui = new LootTableGUI(lootTable, guiManager);
		lootTableGUIs.add(gui);
	}
	
	public void removeLootTable(LootTable lootTable) {
		lootTables.remove(lootTable);
	}
	
	public LootTable getLootTable(String name) {
		for (LootTable lootTable : lootTables) {
			if (lootTable.getName().equals(name)) {
				return lootTable;
			}
		}
		return null;
	}
	
	public LootTableGUI getLootTableGUI(LootTable lootTable) {
		return lootTableGUIs.get(lootTables.indexOf(lootTable));
	}
	
	public List<LootTable> getLootTables() {
		return new LinkedList<>(lootTables);
	}
	
	public LootTableListGUI getListGUI() {
		return lootTableList;
	}
}
