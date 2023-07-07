package me.gorgeousone.simplelootchests.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiAction {
	
	public enum ClickType {
		LEFT, RIGHT;
	}
	private final Player player;
	private final Inventory gui;
	private final ClickType clickType;
	
	public GuiAction(Player player, Inventory gui, ClickType clickType) {
		this.player = player;
		this.gui = gui;
		this.clickType = clickType;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inventory getGui() {
		return gui;
	}
	
	public ClickType getClickType() {
		return clickType;
	}
}
