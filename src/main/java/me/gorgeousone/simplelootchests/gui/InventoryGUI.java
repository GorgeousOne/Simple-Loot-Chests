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

/**
 * What is this?
 */
public class InventoryGUI {
	
	protected String name;
	protected final GUIManager guiManager;
	protected Inventory gui;
	protected final Map<Integer, Consumer<GUIClick>> onClickCalls;
	
	private Consumer<Player> onCloseCall;
	
	public InventoryGUI(String name, GUIManager guiManager) {
		this.name = name;
		this.guiManager = guiManager;
		this.onClickCalls = new HashMap<>();
		this.gui = Bukkit.createInventory(null, 6*9, name);
		guiManager.registerGUI(this);
	}


	/**
	 * Why get this?
	 * @return
	 */
	public Inventory getInventory() {
		return gui;
	}

	/**
	 * Set item where?
	 * @param row
	 * @param column
	 * @param item
	 * @param onClickCall
	 */
	public void setItem(int row, int column, ItemStack item, Consumer<GUIClick> onClickCall) {
		setItem(row*9 + column, item, onClickCall);
	}
	
	public void setItem(int index, ItemStack item, Consumer<GUIClick> onClickCall) {
		//TODO assert index between 0 and 6*9-1
		
		gui.setItem(index, item);
		onClickCalls.remove(index);
		
		if (onClickCall != null) {
			onClickCalls.put(index, onClickCall);
		}
	}

	/**
	 * Who handels click when? public? privately?
	 * @param event
	 * @return
	 */
	//execute the callback of the clicked item
	public boolean handleClick(InventoryClickEvent event) {
		InventoryAction action = event.getAction();
		int slot = event.getSlot();
		
		if (isGUIClick(event)) {
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
			}
			return true;
		}
		//allow moving items in player's own inventory, except moving items from or into gui
		return action == InventoryAction.MOVE_TO_OTHER_INVENTORY ||
		       action == InventoryAction.COLLECT_TO_CURSOR;
	}
	
	protected boolean isGUIClick(InventoryClickEvent event) {
		return !(event.getClickedInventory().getHolder() instanceof Player);
	}
	
	public void handleClose(Consumer<Player> onCloseCall) {
		this.onCloseCall = onCloseCall;
	}
	
	public void onClose(Player player) {
		if (this.onCloseCall != null) {
			onCloseCall.accept(player);
		}
	}
	
	public void open(Player player) {
		player.openInventory(gui);
	}
}
