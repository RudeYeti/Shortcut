package io.github.rudeyeti.shortcut.commands.shortcut;

import io.github.rudeyeti.shortcut.Radius;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildSubcommand {
    public static void execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("shortcut.build") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Usage: You must be a player to perform this command.");
                return;
            } else if (args.length == 1) {
                sender.sendMessage("List of available subcommands:\n" +
                        "roads - Rebuilds the roads with variation of blocks." +
                        "shells - Builds up from the OpenStreetMap Outlines.");
                return;
            }

            if (args[1].matches("r(oads?)?|streets?")) {
                Radius.replaceNear(sender, " 251:7 25%251:7,25%252:7,25%35:7,25%159:9", args, 2);
            } else if (args[1].matches("s(hells?)?|houses?|outlines?")) {
                if (args.length == 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /" + label + " " + args[0] + " " + args[1] + " <height> [radius]");
                } else if (!NumberUtils.isNumber(args[2])) {
                    sender.sendMessage(ChatColor.RED + "Usage: The specified height must be a number.");
                } else if (Integer.parseInt(args[2]) < 2 && Integer.parseInt(args[2]) > 100) {
                    sender.sendMessage(ChatColor.RED + "Usage: The specified height must be from 100 to 2.");
                } else {
                    Player player = (Player) sender;

                    player.performCommand("/gmask >45");

                    for (int i = 0; Integer.parseInt(args[2]) > i; i++) {
                        Radius.replaceNear(sender, " 0 45", args, 3);
                    }

                    player.performCommand("/gmask");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /" + label + " " + args[0] + " <roads> [radius]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: You are missing the correct permission to perform this command.");
        }
    }
}
