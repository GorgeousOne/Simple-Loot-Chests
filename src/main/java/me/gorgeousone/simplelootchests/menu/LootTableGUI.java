package me.gorgeousone.simplelootchests.menu;

import me.gorgeousone.simplelootchests.ItemUtil;
import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import me.gorgeousone.simplelootchests.gui.InventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class LootTableGUI extends InventoryGUI {
	
	private final LootTable lootTable;
	private final ChestContentsGUI contentsGui;
	private Consumer<Player> onReturnCall;
	
	public LootTableGUI(LootTable lootTable, GUIManager guiManager) {
		super("Loot table: " + lootTable.getName(), guiManager);
		this.lootTable = lootTable;
		this.contentsGui = new ChestContentsGUI(lootTable, guiManager);
		
		contentsGui.handleReturn(guiClose -> {
			open(guiClose.getPlayer());
		});
		registerItems();
	}
	
	public LootTable getLootTable() {
		return lootTable;
	}
	
	void handleReturn(Consumer<Player> returnCall) {
		this.onReturnCall = returnCall;
	}
	
	private void registerItems() {
		ItemStack backBtn = ItemUtil.named(Material.ARROW, "Back");
		ItemStack contentsBtn = ItemUtil.named(Material.CHEST, "Edit contents");
		ItemStack getItemBtn = ItemUtil.named(Material.WORKBENCH, "Get chest item");
		ItemStack renameBtn = ItemUtil.named(Material.NAME_TAG, "Rename chest");
		ItemStack deleteBtn = ItemUtil.named(Material.BUCKET, "Delete loot chest");
		
		
		setItem(0, 0, backBtn, guiClick -> {
			if (onReturnCall != null) {
				onReturnCall.accept(guiClick.getPlayer());
			}
		});
		setItem(1, 4, contentsBtn, guiClick -> {
			contentsGui.open(guiClick.getPlayer());
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
