package com.github.xhexed.uniqueborder;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

@AllArgsConstructor
public class WorldBorderConfig {
    private UniqueBorders plugin;
    private String name;
    private double
            centerX,
            centerY,
            baseSize,
            expandSize;
    private long expandTime;

    public void updateWorldBorder() {
        World world = Bukkit.getServer().getWorld(name);
        if (world == null) return;
        WorldBorder border = world.getWorldBorder();
        border.setCenter(centerX, centerY);
        border.setSize(baseSize + expandSize * plugin.getPlayerCount().get(), expandTime);
    }

    public void expandWorldBorder() {
        World world = Bukkit.getServer().getWorld(name);
        if (world == null) return;
        WorldBorder border = world.getWorldBorder();
        int playerCount = plugin.getPlayerCount().incrementAndGet();
        border.setSize(baseSize + expandSize * playerCount, expandTime);
    }
}
