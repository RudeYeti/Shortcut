package io.github.rudeyeti.shortcut.commands;

import org.bukkit.command.CommandSender;

import static io.github.rudeyeti.shortcut.Shortcut.*;

public class InfoSubcommand {
    public static void execute(CommandSender sender) {
        sender.sendMessage("General information:\n" +
                "Author - " + plugin.getDescription().getAuthors().get(0) + "\n" +
                "Version - " + plugin.getDescription().getVersion());
    }
}
