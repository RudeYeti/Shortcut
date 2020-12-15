package io.github.rudeyeti.shortcut;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Radius {

    public static String get(CommandSender sender, String[] args) {
        if (args.length == 3) {
            if (NumberUtils.isNumber(args[2])) {
                if (Integer.parseInt(args[2]) > 100) {
                    sender.sendMessage(ChatColor.RED + "Usage: The specified radius must be 100 or less.");
                    return "";
                }
                return args[2];
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: The specified radius must be a number.");
                return "";
            }
        } else {
            return Integer.toString(Config.defaultRadius);
        }
    }

    public static void replaceNear(CommandSender sender, String[] command, String[] args) {
        Player player = (Player) sender;
        String radius = get(sender, args);

        if (!radius.isEmpty()) {
            player.performCommand(command[0] + radius + command[1]);
        }
    }
}
