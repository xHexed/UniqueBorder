package com.github.xhexed.uniqueborder;

import com.github.xhexed.uniqueborder.command.CommandManager;
import com.github.xhexed.uniqueborder.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueBorders extends JavaPlugin {
    @Getter
    private ConfigManager configManager;
    @Getter
    private AtomicInteger playerCount;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);

        PluginCommand command = Objects.requireNonNull(getCommand("uniqueborders"));
        CommandManager commandManager = new CommandManager(this);
        command.setExecutor(commandManager);
        command.setTabCompleter(commandManager);

        reloadPlugin();
    }

    public void reloadPlugin() {
        HandlerList.unregisterAll(this);
        playerCount = new AtomicInteger(getServer().getOfflinePlayers().length);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        configManager.reloadConfig();
    }
}
