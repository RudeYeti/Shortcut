package io.github.rudeyeti.shortcut.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShortcutCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("List of available subcommands:\n" +
                               "info - Shows details about the author and the version of this plugin.\n" +
                               "reload - Updates any values that were modified in the configuration.\n" +
                               "clear - Removes various blocks from within a radius.");
        } else if (args[0].matches("i(nfo)?(rmation)?|authors?|ver(sion)?")) {
            InfoSubcommand.execute(sender);
        } else if (args[0].matches("r(e?(load|start|boot))?|(en|dis)able")) {
            ReloadSubcommand.execute(sender);
        } else if (args[0].matches("c(lear)?|de(lete|stroy)|remove|fuckoff")) {
            ClearSubcommand.execute(sender, label, args);
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <info | reload | clear>");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("info", "reload", "clear"), new ArrayList<>());
        } else if (args[0].matches("c(lear)?|de(lete|stroy)|remove|fuckoff") && args.length <= 2) {
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("all", "nature", "trees", "caves", "mobs"), new ArrayList<>());
        }
        return Collections.singletonList("");
    }
}