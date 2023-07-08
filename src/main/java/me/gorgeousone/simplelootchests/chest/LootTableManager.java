package me.gorgeousone.simplelootchests.chest;

import me.gorgeousone.simplelootchests.LootTableListGUI;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;

public class LootTableManager {
	
	private final JavaPlugin plugin;
	private final List<LootTable> lootTables;
	
	private final GUIManager guiManager;
	private final LootTableListGUI lootTableList;
	
	public LootTableManager(JavaPlugin plugin, GUIManager guiManager) {
		this.plugin = plugin;
		this.guiManager = guiManager;
		this.lootTables = new LinkedList<>();
		this.lootTableList = new LootTableListGUI(plugin, this);
		
		registerGUIs();
	}
	
	private void registerGUIs() {
		guiManager.registerGUI(lootTableList);
	}
	
	public void addLootTable(LootTable lootTable) {
		lootTables.add(lootTable);
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
	
	public List<LootTable> getLootTables() {
		return new LinkedList<>(lootTables);
	}
	
	public LootTableListGUI getListGUI() {
		return lootTableList;
	}
}
