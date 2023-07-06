package me.gorgeousone.simplelootchests.chest;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

// track and manage placed chests
public class ChestManager {
	final Map<String, Set<LootChest>> chestsPerWorld;

	public ChestManager() {
		this.chestsPerWorld = new HashMap<>();
	}

	public LootChest placeChest(Location blockPos, LootTable loot) {
		LootChest chest = new LootChest(blockPos, loot);
		String worldId = blockPos.getWorld().getName();

		chestsPerWorld.computeIfAbsent(worldId, uuid -> new HashSet<>());
		chestsPerWorld.get(worldId).add(chest);
		return chest;
	}

	public LootChest findChest(Location blockPos) {
		return null;
	}

	public void removeChest(LootChest chest) {}
}
