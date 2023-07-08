package me.gorgeousone.simplelootchests.chest;

import me.gorgeousone.simplelootchests.ItemUtil;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class LootItem {

	private static DecimalFormat percentFormat = new DecimalFormat("#.0");
	private ItemStack item;
	private ItemStack displayItem;
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
	
	public ItemStack getDisplayItem(LootTable table) {
		return ItemUtil.addLore(
				item,
				"Weight: " + weight,
				"Chance to appear in chest: " + percentFormat.format(100d * weight / table.totalWeight()) + "%");
	}
}
