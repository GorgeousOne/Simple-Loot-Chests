package me.gorgeousone.simplelootchests.chest;

import me.gorgeousone.simplelootchests.BlockPos;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LootChest {
	private final BlockPos blockPos;
	private final LootTable loot;

	public LootChest(BlockPos blockPos, LootTable loot) {
		this.blockPos = blockPos.clone();
		this.loot = loot;
	}

	public BlockPos getBlockPos() {
		return blockPos.clone();
	}

	public LootTable getLoot() {
		return loot;
	}
	
	public void open(Player player) {
	}
}
