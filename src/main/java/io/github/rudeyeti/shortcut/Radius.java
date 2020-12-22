package io.github.rudeyeti.shortcut;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Radius {

    public static String get(CommandSender sender, String[] args, int radiusIndex) {
        if (args.length == radiusIndex + 1) {
            if (NumberUtils.isNumber(args[radiusIndex])) {
                if (Integer.parseInt(args[radiusIndex]) < 1 && Integer.parseInt(args[radiusIndex]) > 100) {
                    sender.sendMessage(ChatColor.RED + "Usage: The specified radius must be from 100 to 1.");
                    return "";
                }
                return args[radiusIndex];
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: The specified radius must be a number.");
                return "";
            }
        } else {
            return Integer.toString(Config.defaultRadius);
        }
    }

    public static void replaceNear(CommandSender sender, String command, String[] args, int radiusIndex) {
        Player player = (Player) sender;
        String radius = get(sender, args, radiusIndex);

        if (!radius.isEmpty()) {
            player.performCommand("/replacenear " + radius + command);
        }
    }
}
