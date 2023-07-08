package me.gorgeousone.simplelootchests.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryGUI {
	
	private String name;
	private Inventory gui;
	private final Map<Integer, Consumer<GUIClick>> onClickCalls;
	
	private Consumer<Player> onCloseCall;
	
	public InventoryGUI(String name) {
		this.name = name;
		this.onClickCalls = new HashMap<>();
		this.gui = Bukkit.createInventory(null, 6*9, name);
	}
	
	
	public Inventory getInventory() {
		return gui;
	}
	
	public void setItem(int row, int column, ItemStack item, Consumer<GUIClick> onClickCall) {
		//TODO assert index between 0 and 6*9-1
		int index = row*9 + column;
		gui.setItem(index, item);
		onClickCalls.remove(index);
		
		if (onClickCall != null) {
			onClickCalls.put(index, onClickCall);
		}
	}
	//execute the callback of the clicked item
	
	public boolean handleClick(InventoryClickEvent event) {
		InventoryAction action = event.getAction();

		//allow moving items in player's own inventory, except moving items from or to gui
		if (event.getClickedInventory().getHolder() instanceof Player
		    && action != InventoryAction.MOVE_TO_OTHER_INVENTORY
		    && action != InventoryAction.COLLECT_TO_CURSOR) {
			return false;
		}
		int slot = event.getSlot();
		
		if (onClickCalls.containsKey(slot)) {
			//only allow left and right click as interactions
			if (action != InventoryAction.PICKUP_ALL && action != InventoryAction.PICKUP_HALF) {
				return true;
			}
			onClickCalls.get(slot).accept(new GUIClick(
					(Player) event.getWhoClicked(),
					event.getInventory(),
					event.getAction() == InventoryAction.PICKUP_ALL ? GUIClick.ClickType.LEFT : GUIClick.ClickType.RIGHT
			));
			return true;
		}
		return true;
	}
	
	public void onClose(Consumer<Player> onCloseCall) {
		this.onCloseCall = onCloseCall;
	}
	
	public void close(Player player) {
		if (this.onCloseCall != null) {
			onCloseCall.accept(player);
		}
	}
	
	public void open(Player player) {
		player.openInventory(gui);
	}
}
