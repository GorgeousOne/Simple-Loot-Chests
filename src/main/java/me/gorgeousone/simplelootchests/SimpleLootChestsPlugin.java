package me.gorgeousone.simplelootchests;

import me.gorgeousone.simplelootchests.cmdframework.handler.CommandHandler;
import me.gorgeousone.simplelootchests.command.ConfigCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleLootChestsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    void registerCommands() {
        ConfigCommand configCmd = new ConfigCommand(this);
        
        CommandHandler cmdHandler = new CommandHandler(this);
        cmdHandler.registerCommand(configCmd);
    }
}
