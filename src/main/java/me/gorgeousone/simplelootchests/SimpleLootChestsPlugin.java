package me.gorgeousone.simplelootchests;

import me.gorgeousone.simplelootchests.chest.LootTableManager;
import me.gorgeousone.simplelootchests.cmdframework.command.ParentCommand;
import me.gorgeousone.simplelootchests.cmdframework.handler.CommandHandler;
import me.gorgeousone.simplelootchests.command.ListChestsCommand;
import me.gorgeousone.simplelootchests.gui.GUIManager;
import me.gorgeousone.simplelootchests.gui.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleLootChestsPlugin extends JavaPlugin {

    private GUIManager guiManager;
    private LootTableManager lootTableManager;
    
    @Override
    public void onEnable() {
        guiManager = new GUIManager();
        lootTableManager = new LootTableManager(this, guiManager);
        
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        ParentCommand lootChestCmd = new ParentCommand("lootchest");
        lootChestCmd.setPlayerRequired(true);
        lootChestCmd.addAlias("lc");
        lootChestCmd.addAlias("loot");
        
        ListChestsCommand listChestsCmd = new ListChestsCommand(this, lootTableManager.getListGUI());
        
        lootChestCmd.addChild(listChestsCmd);
        
        CommandHandler cmdHandler = new CommandHandler(this);
        cmdHandler.registerCommand(lootChestCmd);
    }
    
    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryListener(this, guiManager), this);
    }
}
