package io.github.rudeyeti.shortcut;

import io.github.rudeyeti.shortcut.commands.ShortcutCommand;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Shortcut extends JavaPlugin {

    public static Plugin plugin;
    public static Configuration config;
    public static Logger logger;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        this.saveDefaultConfig();
        config = this.getConfig();
        Config.validateConfig();
        Config.updateConfig();

        plugin = getPlugin(this.getClass());
        logger = this.getLogger();
        this.getCommand("shortcut").setExecutor(new ShortcutCommand());
    }
}
