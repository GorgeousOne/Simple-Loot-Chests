package me.gorgeousone.simplelootchests.chest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * What blocks do you listen to?
 */
public class BlockListener {
	
	private final ChestManager chestManager;
	
	public BlockListener(ChestManager chestManager) {
		this.chestManager = chestManager;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		Block clickedBlock = event.getClickedBlock();
		
		if (clickedBlock == null && clickedBlock.getType() != Material.CHEST) {
			return;
		}
		LootChest lootChest = chestManager.findChest(clickedBlock.getLocation());
		
		if (lootChest != null) {
			event.setCancelled(true);
			lootChest.open(event.getPlayer());
		}
	}
}
