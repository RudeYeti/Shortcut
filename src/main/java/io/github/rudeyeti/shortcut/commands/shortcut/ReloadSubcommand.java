package io.github.rudeyeti.shortcut.commands.shortcut;

import io.github.rudeyeti.shortcut.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import static io.github.rudeyeti.shortcut.Shortcut.*;

public class ReloadSubcommand {
    public static void execute(CommandSender sender) {
        if (sender.hasPermission("shortcut.reload") || sender.isOp()) {
            Configuration oldConfig = config;
            plugin.reloadConfig();
            config = plugin.getConfig();

            if (!Config.validateConfig()) {
                config = oldConfig;
                Config.updateConfig();
                sender.sendMessage("The configuration was invalid, reverting back to the previous state.");
            } else {
                sender.sendMessage("The plugin has been successfully reloaded.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: You are missing the correct permission to perform this command.");
        }
    }
}
