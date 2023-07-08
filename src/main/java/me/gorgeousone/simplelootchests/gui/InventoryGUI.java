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
	
	public void handleClick(InventoryClickEvent event) {
		int slot = event.getSlot();
		if (onClickCalls.containsKey(slot)) {
			onClickCalls.get(slot).accept(new GUIClick(
					(Player) event.getWhoClicked(),
					event.getInventory(),
					event.getAction() == InventoryAction.PICKUP_ALL ? GUIClick.ClickType.LEFT : GUIClick.ClickType.RIGHT
			));
		}
	}
	
	public void onClose(Consumer<Player> onCloseCall) {
		this.onCloseCall = onCloseCall;
	}
	
	public void close(Player player) {
		onCloseCall.accept(player);
	}
	
	public void open(Player player) {
		player.openInventory(gui);
	}
}
