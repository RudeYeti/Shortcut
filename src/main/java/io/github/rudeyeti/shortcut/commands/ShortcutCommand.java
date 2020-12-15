package io.github.rudeyeti.shortcut.commands;

import io.github.rudeyeti.shortcut.commands.shortcut.BuildSubcommand;
import io.github.rudeyeti.shortcut.commands.shortcut.ClearSubcommand;
import io.github.rudeyeti.shortcut.commands.shortcut.InfoSubcommand;
import io.github.rudeyeti.shortcut.commands.shortcut.ReloadSubcommand;
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
                               "clear - Removes various blocks from within a radius.\n" +
                               "build - Builds and improves numerous types of structures.");
        } else if (args[0].matches("i(nfo)?(rmation)?|authors?|ver(sion)?")) {
            InfoSubcommand.execute(sender);
        } else if (args[0].matches("r(e?(load|start|boot))?|(en|dis)able")) {
            ReloadSubcommand.execute(sender);
        } else if (args[0].matches("c(lear)?|de(lete|stroy)|remove|fuckoff")) {
            ClearSubcommand.execute(sender, label, args);
        } else if (args[0].matches("(re)?b(uilds?)?|create|improve")) {
            BuildSubcommand.execute(sender, label, args);
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <info | reload | clear | build>");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("info", "reload", "clear", "build"), new ArrayList<>());
        } else if (args.length <= 2) {
            if (args[0].matches("c(lear)?|de(lete|stroy)|remove|fuckoff")) {
                return StringUtil.copyPartialMatches(args[1], Arrays.asList("all", "nature", "trees", "caves", "mobs"), new ArrayList<>());
            } else if (args[0].matches("(re)?b(uilds?)?|create|improve")) {
                return StringUtil.copyPartialMatches(args[1], Arrays.asList("roads"), new ArrayList<>());
            }
        }
        return Collections.singletonList("");
    }
}