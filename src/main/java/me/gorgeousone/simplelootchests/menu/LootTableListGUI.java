package me.gorgeousone.simplelootchests.menu;

import me.gorgeousone.simplelootchests.ItemUtil;
import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.chest.LootTableManager;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import me.gorgeousone.simplelootchests.gui.InventoryGUI;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class LootTableListGUI extends InventoryGUI {
	
	private final JavaPlugin plugin;
	private final LootTableManager lootTableManager;
	private AnvilGUI.Builder namingGUI;
	
	public LootTableListGUI(JavaPlugin plugin, LootTableManager lootTableManager, GUIManager guiManager) {
		super("List of loot chests", guiManager);
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
		LootTableGUI gui = lootTableManager.getLootTableGUI(lootTable);
		//TODO handle multi page logic
		setItem(index, ItemUtil.named(Material.CHEST, lootTable.getName()), guiClick -> {
			gui.open(guiClick.getPlayer());
		});
	}
	
	private void addCreateBtn(int index) {
		setItem(index, ItemUtil.named(Material.BOOK_AND_QUILL, "Create new loot chest"), guiClick -> {
			namingGUI.open(guiClick.getPlayer());
		});
	}
	
	private void createNamingGUI() {
		namingGUI = new AnvilGUI.Builder()
				.plugin(plugin)
				.title("What should the loot chest be called?")
				.itemLeft(new ItemStack(Material.NAME_TAG));
		
		namingGUI.text("a");
		namingGUI.onClick((slot, stateSnapshot) -> {
			if (slot == AnvilGUI.Slot.OUTPUT) {
				//TODO check if name is valid
				LootTable lootTable = new LootTable(stateSnapshot.getText());
				createLootTableGUI(lootTable).open(stateSnapshot.getPlayer());
//				return Arrays.asList(AnvilGUI.ResponseAction.close());
			}
			return Collections.emptyList();
		});
	}
	
	private LootTableGUI createLootTableGUI(LootTable lootTable) {
		lootTableManager.addLootTable(lootTable);
		LootTableGUI gui = lootTableManager.getLootTableGUI(lootTable);
		gui.handleReturn(player -> {
			open(player);
		});
		return gui;
	}
}
