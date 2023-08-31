package me.gorgeousone.simplelootchests;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemUtil {

	/**
	 * Name what part of an item?
	 * @param material
	 * @param displayName
	 * @return
	 */
	public static ItemStack named(Material material, String displayName) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		return item;
	}

	/**
	 * Does this adds or replace lore?
	 * @param item
	 * @param loreLines
	 * @return
	 */
	public static ItemStack addLore(ItemStack item, String... loreLines) {
		ItemStack copy = item.clone();
		ItemMeta meta = copy.getItemMeta();
		List<String> lore = meta.getLore();
		lore.addAll(Arrays.asList(loreLines));
		meta.setLore(lore);
		copy.setItemMeta(meta);
		return copy;
	}
}
