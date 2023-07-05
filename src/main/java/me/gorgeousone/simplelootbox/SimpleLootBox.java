package me.gorgeousone.simplelootbox;

import me.gorgeousone.simplelootbox.cmdframework.handler.CommandHandler;
import me.gorgeousone.simplelootbox.command.ConfigCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleLootBox extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    void registerCommands() {
        ConfigCommand configCmd = new ConfigCommand();
        
        CommandHandler cmdHandler = new CommandHandler(this);
        cmdHandler.registerCommand(configCmd);
    }
}
