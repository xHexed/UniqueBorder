package com.github.xhexed.uniqueborder.command;

import com.github.xhexed.uniqueborder.UniqueBorders;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private UniqueBorders plugin;
    private List<String> commands = Collections.singletonList("reload");
    private List<String> playerCommands = new ArrayList<>(commands);

    public CommandManager(UniqueBorders plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§7Usage: /ub reload");
            return true;
        }
        else if (args.length == 1) {
            if ("reload".equalsIgnoreCase(args[0])) {
                sender.sendMessage("§aReloading plugin...");
                plugin.reloadPlugin();
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1) {
            return null;
        }

        Collection<String> commandList = commands;
        if (commandSender instanceof Player) {
            commandList = playerCommands;
        }

        List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(strings[0], commandList, completions);
        Collections.sort(completions);

        return completions;
    }
}
