package me.gorgeousone.simplelootchests.chest;

import java.util.LinkedList;
import java.util.List;

public class LootTableManager {
	
	private final List<LootTable> lootTables;
	
	public LootTableManager() {
		this.lootTables = new LinkedList<>();
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
}
