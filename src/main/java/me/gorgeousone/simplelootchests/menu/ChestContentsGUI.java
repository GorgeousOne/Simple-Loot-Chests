package me.gorgeousone.simplelootchests.menu;

import me.gorgeousone.simplelootchests.ItemUtil;
import me.gorgeousone.simplelootchests.chest.LootItem;
import me.gorgeousone.simplelootchests.chest.LootTable;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import me.gorgeousone.simplelootchests.gui.InventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ChestContentsGUI extends InventoryGUI {
	
	private final LootTable lootTable;
	private Consumer<Player> onReturnCall;
	
	public ChestContentsGUI(LootTable lootTable, GUIManager guiManager) {
		super(lootTable.getName() + " contents", guiManager);
		this.lootTable = lootTable;
	}
	
	private void loadItems() {
		ItemStack backBtn = ItemUtil.named(Material.ARROW, "Back");
		
		setItem(0, 0, backBtn, guiClick -> {
			if (onReturnCall != null) {
				onReturnCall.accept(guiClick.getPlayer());
			}
		});
		for (LootItem lootItem : lootTable.getItems()) {
			gui.addItem(lootItem.getDisplayItem(lootTable));
		}
	}
	
	@Override
	public void open(Player player) {
		loadItems();
		super.open(player);
	}
	
	public void handleReturn(Consumer<Player> returnCall) {
		this.onReturnCall = returnCall;
	}
	
//	@Override
//	public boolean handleClick(InventoryClickEvent event) {
//		int slot = event.getSlot();
//
//		if (isGUIClick(event) && onClickCalls.containsKey(slot)) {
//			return super.handleClick(event);
//		}
//
//		return true;
//	}
}
