package io.github.rudeyeti.shortcut.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.rudeyeti.shortcut.Shortcut.*;

public class ClearSubcommand {

    public static String radius(CommandSender sender, String[] args) {
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
            return Integer.toString(defaultRadius);
        }
    }

    public static void replaceNear(CommandSender sender, String[] command, String[] args) {
        Player player = (Player) sender;
        String radius = radius(sender, args);

        if (!radius.isEmpty()) {
            player.performCommand(command[0] + radius + command[1]);
        }
    }

    public static void caves(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String radius = radius(sender, args);

        if (!radius.isEmpty()) {
            int x = player.getLocation().getBlockX() + Integer.parseInt(radius);

            player.performCommand("/sel sphere");
            player.performCommand("/pos1");
            player.performCommand("/pos2 " + x + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ());
            player.performCommand("/replace \"0,4,8,9,10,11,48,79 <0\" dirt");
            player.performCommand("/naturalize");
            player.performCommand("/sel");
        }
    }

    public static void execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("shortcut.clear") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Usage: You must be a player to perform this command.");
                return;
            } else if (args.length == 1) {
                sender.sendMessage("List of available subcommands:\n" +
                        "all - Removes everything listed below.\n" +
                        "nature - Removes grass, flowers, mushrooms, sugarcane, and pumpkins.\n" +
                        "trees - Removes all types of trees, including leaves.\n" +
                        "caves - Removes all types of caves, ravines, lakes, and dungeons.\n" +
                        "mobs - Removes every type of entity, excluding players.\n");
                return;
            }

            if (args[1].matches("a(ll)?|everything")) {
                replaceNear(sender, new String[]{"/replacenear ", " 31,37,38,39,40,81,83,86,175 0"}, args);
                replaceNear(sender, new String[]{"/replacenear ", " 17,18,161,162 0"}, args);
                caves(sender, args);
                replaceNear(sender, new String[]{"butcher ", " -a"}, args);
            } else if (args[1].matches("n(ature)?|grass|flowers?")) {
                replaceNear(sender, new String[]{"/replacenear ", " 31,37,38,39,40,81,83,86,175 0"}, args);
            } else if (args[1].matches("t(rees?)?|lea(f|ves)")) {
                replaceNear(sender, new String[]{"/replacenear ", " 17,18,161,162 0"}, args);
            } else if (args[1].matches("c(aves?)?")) {
                caves(sender, args);
            } else if (args[1].matches("m(obs?)?")) {
                replaceNear(sender, new String[]{"butcher ", " -a"}, args);
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /" + label + " " + args[0] + " <all | nature | trees | caves | mobs> [radius]");
                return;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: You are missing the correct permission to perform this command.");
        }
    }
}
