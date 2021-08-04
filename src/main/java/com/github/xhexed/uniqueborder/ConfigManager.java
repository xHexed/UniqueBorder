package com.github.xhexed.uniqueborder;

import com.tchristofferson.configupdater.ConfigUpdater;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigManager {
    private UniqueBorders plugin;
    @Getter
    private Map<String, WorldBorderConfig> worldBorderConfigs;

    public ConfigManager(UniqueBorders plugin) {
        this.plugin = plugin;
        worldBorderConfigs = new HashMap<>();
        plugin.saveDefaultConfig();
    }

    public void reloadConfig() {
        FileConfiguration config = plugin.getConfig();

        if (config.getBoolean("auto-update", true)) {
            try {
                ConfigUpdater.update(plugin, "config.yml", new File(plugin.getDataFolder(), "config.yml"), Collections.singletonList("worlds"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ConfigurationSection worldSection = config.getConfigurationSection("worlds");
        for (String key : Objects.requireNonNull(worldSection).getKeys(false)) {
            ConfigurationSection worldConfig = Objects.requireNonNull(worldSection.getConfigurationSection(key));
            double centerX = worldConfig.getDouble("center-x");
            double centerY = worldConfig.getDouble("center-z");
            double baseSize = worldConfig.getDouble("base-size");
            double expandSize = worldConfig.getDouble("expand-size");
            long expandTime = worldConfig.getLong("expand-time");
            WorldBorderConfig worldBorderConfig = new WorldBorderConfig(plugin, key, centerX, centerY, baseSize, expandSize, expandTime);
            worldBorderConfigs.put(key, worldBorderConfig);
            worldBorderConfig.updateWorldBorder();
        }
    }
}
