package me.gorgeousone.simplelootchests.chest;

import me.gorgeousone.simplelootchests.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
	
	public ItemStack getChestItem() {
		return ItemUtil.named(Material.CHEST, name);
	}
	
	public void togglePerPlayerLoot() {
	}
	
	public boolean isPerPlayerLoot() {
		return isPerPlayerLoot();
	}
}
