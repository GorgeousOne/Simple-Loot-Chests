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

public class InventoryGui {
	
	private String name;
	private Inventory gui;
	private final Map<Integer, Consumer<GuiAction>> onClickCalls;
	
	private Consumer<Player> onCloseCall;
	
	public InventoryGui(String name) {
		this.name = name;
		this.onClickCalls = new HashMap<>();
		this.gui = Bukkit.createInventory(null, 6*9, name);
	}
	
	
	public Inventory getInventory() {
		return gui;
	}
	
	public void setItem(int index, ItemStack item, Consumer<GuiAction> onClickCall) {
		//TODO assert index between 0 and 6*9-1
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
			onClickCalls.get(slot).accept(new GuiAction(
					(Player) event.getWhoClicked(),
					event.getInventory(),
					event.getAction() == InventoryAction.PICKUP_ALL ? GuiAction.ClickType.LEFT : GuiAction.ClickType.RIGHT
			));
		}
	}
	
	public void onClose(Consumer<Player> onCloseCall) {
		this.onCloseCall = onCloseCall;
	}
	
	public void close(Player player) {
		onCloseCall.accept(player);
	}
}
