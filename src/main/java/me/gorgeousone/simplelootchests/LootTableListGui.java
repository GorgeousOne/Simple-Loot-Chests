package me.gorgeousone.simplelootchests;

import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.chest.LootTableManager;
import me.gorgeousone.simplelootchests.gui.InventoryGui;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LootTableListGui extends InventoryGui {
	
	private final LootTableManager lootTableManager;
	
	public LootTableListGui(LootTableManager lootTableManager) {
		super("List of loot chests");
		this.lootTableManager = lootTableManager;
	}
	
	private void loadChests() {
		List<LootTable> lootTables = lootTableManager.getLootTables();
		
		for (int i = 0; i < lootTables.size(); i++) {
			addLootTableButton(i, lootTables.get(i));
		}
	}
	
	private void addLootTableButton(int index, LootTable lootTable) {
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(lootTable.getName());
		item.setItemMeta(meta);
		
		setItem(index, item, guiAction -> {
			//TODO open loot table gui
		});
	}
}
