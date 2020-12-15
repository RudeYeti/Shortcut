package io.github.rudeyeti.shortcut.commands.shortcut;

import io.github.rudeyeti.shortcut.Radius;
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
                                   "roads - Rebuilds the roads with variation of blocks.");
                return;
            }

            if (args[1].matches("r(oads?)?|streets?")) {
                Radius.replaceNear(sender, new String[]{"/replacenear ", " 251:7 25%251:7,25%252:7,25%35:7,25%159:9"}, args);
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /" + label + " " + args[0] + " <roads> [radius]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: You are missing the correct permission to perform this command.");
        }
    }
}
