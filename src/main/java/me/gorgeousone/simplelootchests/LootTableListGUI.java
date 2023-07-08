package me.gorgeousone.simplelootchests;

import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.chest.LootTableManager;
import me.gorgeousone.simplelootchests.gui.InventoryGUI;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class LootTableListGUI extends InventoryGUI {
	
	private final JavaPlugin plugin;
	private final LootTableManager lootTableManager;
	private AnvilGUI.Builder namingGUI;
	
	public LootTableListGUI(JavaPlugin plugin, LootTableManager lootTableManager) {
		super("List of loot chests");
		this.plugin = plugin;
		this.lootTableManager = lootTableManager;
		
		createNamingGUI();
	}
	
	@Override
	public void open(Player player) {
		loadChests();
		super.open(player);
	}
	
	private void loadChests() {
		getInventory().clear();
		List<LootTable> lootTables = lootTableManager.getLootTables();
		
		int i = 0;
		for (; i < lootTables.size(); i++) {
			addLootTableBtn(i, lootTables.get(i));
		}
		addCreateBtn(i);
	}
	
	private void addLootTableBtn(int index, LootTable lootTable) {
		//TODO handle multi page logic
		int row = index / 9;
		int column = index % 9;
		setItem(row, column, ItemUtil.named(Material.CHEST, lootTable.getName()), guiClick -> {});
	}
	
	private void addCreateBtn(int index) {
		int row = index / 9;
		int column = index % 9;
		
		setItem(row, column, ItemUtil.named(Material.BOOK_AND_QUILL, "Create new loot chest"), guiClick -> {
			namingGUI.open(guiClick.getPlayer());
		});
	}
	
	private void createNamingGUI() {
		namingGUI = new AnvilGUI.Builder()
				.plugin(plugin)
				.title("What should the loot chest be called?")
				.itemLeft(new ItemStack(Material.NAME_TAG));
		
		namingGUI.onClick((slot, stateSnapshot) -> {
			lootTableManager.addLootTable(new LootTable(stateSnapshot.getText()));
			return Arrays.asList(AnvilGUI.ResponseAction.close());
		});
	}
}
