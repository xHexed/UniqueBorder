package com.github.xhexed.uniqueborder.listener;

import com.github.xhexed.uniqueborder.UniqueBorders;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    private UniqueBorders plugin;

    public PlayerListener(UniqueBorders plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()) return;
        plugin.getConfigManager().getWorldBorderConfigs().forEach((worldName, config) -> config.expandWorldBorder());
    }
}
