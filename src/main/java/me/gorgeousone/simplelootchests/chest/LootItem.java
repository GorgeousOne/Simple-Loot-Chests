package me.gorgeousone.simplelootchests.chest;

import org.bukkit.inventory.ItemStack;

public class LootItem {

	private ItemStack item;
	private int weight;

	public LootItem(ItemStack item, int weight) {
		this.item = item.clone();
		this.weight = weight;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
