package me.gorgeousone.simplelootchests.chest;

import java.util.LinkedList;
import java.util.List;

public class LootTable {

	private String name;
	private final List<LootItem> items;
	private boolean isPerPlayer;
	
	public LootTable(String name) {
		this.name = name;
		this.items = new LinkedList<>();
	}
	
	public String getName() {
		return name;
	}
}
