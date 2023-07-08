package me.gorgeousone.simplelootchests;

import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.gui.InventoryGUI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class LootTableGUI extends InventoryGUI {
	
	private final LootTable lootTable;
	
	public LootTableGUI(LootTable lootTable) {
		super("Loot table: " + lootTable.getName());
		this.lootTable = lootTable;
		
		loadItems();
	}
	
	private void loadItems() {
		ItemStack backBtn = ItemUtil.named(Material.ARROW, "Back");
		ItemStack contentsBtn = ItemUtil.named(Material.CHEST, "Edit contents");
		ItemStack getItemBtn = ItemUtil.named(Material.WORKBENCH, "Get chest item");
		ItemStack renameBtn = ItemUtil.named(Material.NAME_TAG, "Rename chest");
		ItemStack deleteBtn = ItemUtil.named(Material.BUCKET, "Delete loot chest");
		
		
		setItem(0, 0, backBtn, guiClick -> {
			close(guiClick.getPlayer());
			//TODO reopen loot table list gui
		});
		setItem(1, 4, contentsBtn, guiClick -> {
			//TODO open loot table list gui
		});
		setItem(3, 1, getItemBtn, guiClick -> {
			guiClick.getPlayer().getInventory().addItem(lootTable.getChestItem());
		});
		setItem(3, 3, renameBtn, guiClick -> {
			//TODO open anvil gui for renaming
		});
		setPerPlayerToggle(3, 5);
		setItem(3, 7, deleteBtn, guiClick -> {
			//TODO delete loot table from list
		});
	}
	
	private void setPerPlayerToggle(int row, int column) {
		setItem(row, column, getPerPlayerLootToggleItem(), guiClick -> {
			lootTable.togglePerPlayerLoot();
			setPerPlayerToggle(row, column);
		});
	}
	
	private ItemStack getPerPlayerLootToggleItem() {
		if (lootTable.isPerPlayerLoot()) {
			return ItemUtil.named(Material.REDSTONE, "Per player loot: ON");
		} else {
			return ItemUtil.named(Material.SULPHUR, "Per player loot: OFF");
		}
	}
}
