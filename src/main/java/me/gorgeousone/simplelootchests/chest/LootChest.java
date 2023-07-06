package me.gorgeousone.simplelootchests.chest;

import org.bukkit.Location;

public class LootChest {
	private final Location blockPos;
	private final LootTable loot;

	public LootChest(Location blockPos, LootTable loot) {
		this.blockPos = blockPos.clone();
		this.loot = loot;
	}

	public Location getBlockPos() {
		return blockPos.clone();
	}

	public LootTable getLoot() {
		return loot;
	}
}
